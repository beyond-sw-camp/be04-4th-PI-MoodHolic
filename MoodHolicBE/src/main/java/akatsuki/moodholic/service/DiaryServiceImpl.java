package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.*;
import akatsuki.moodholic.dto.ResponseDiary;
import akatsuki.moodholic.etc.DataParse;
import akatsuki.moodholic.repository.*;
import akatsuki.moodholic.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class  DiaryServiceImpl implements DiaryService{
    private final DiaryDAO diaryDAO;
    private final ChatGPTService chatGPTService;
    private final DiaryFoodService diaryFoodService;
    private final DiaryEmotionService diaryEmotionService;
    private final DiaryMovieService diaryMovieService;
    private final DiaryMusicService diaryMusicService;
    private final CommentService commentService;
    private final FoodService foodService;
    private final MovieService movieService;
    private final MusicService musicService;

    @Autowired
    public DiaryServiceImpl(DiaryDAO diaryDAO, ChatGPTService chatGPTService, DiaryFoodService diaryFoodService, DiaryEmotionService diaryEmotionService, DiaryMovieService diaryMovieService, DiaryMusicService diaryMusicService, CommentService commentService, FoodService foodService, MovieService movieService, MusicService musicService) {
        this.diaryDAO = diaryDAO;
        this.chatGPTService = chatGPTService;
        this.diaryFoodService = diaryFoodService;
        this.diaryEmotionService = diaryEmotionService;
        this.diaryMovieService = diaryMovieService;
        this.diaryMusicService = diaryMusicService;
        this.commentService = commentService;
        this.foodService = foodService;
        this.movieService = movieService;
        this.musicService = musicService;
    }

    @Override
    public ResponseDiary getDiary(int diaryId) {
        Diary diary = diaryDAO.findById(diaryId).orElseThrow();
        DiaryEmotion emotion = diaryEmotionService.findEmotionByDiaryId(diaryId);
        DiaryFood food = diaryFoodService.findFoodByDiaryId(diaryId);
        DiaryMovie movie = diaryMovieService.findMovieByDiaryId(diaryId);
        DiaryMusic music = diaryMusicService.findMusicByDiaryId(diaryId);
        Comment comment = commentService.findCommentByDiaryId(diaryId);
        String emotionValue= new String();

        if(emotion !=null) {
            if (emotion.getEmotionId() < 4)  emotionValue= "나쁨";
            else if(emotion.getEmotionId()<7) emotionValue="보통";
            else emotionValue="좋음";
        }
        return new ResponseDiary(diary,emotionValue,food.getFoodId(),movie.getMovieId(),music.getMusicId(),comment);
    }


    @Override
    @Transactional
    public String postDiary(Diary requestdiary) {
        Diary findDiary = diaryDAO.findByMemberMemberIdAndDate(requestdiary.getMember().getMemberId(),requestdiary.getDate());

        if(findDiary!=null){
            requestdiary.setDiaryId(findDiary.getDiaryId());
            if(findDiary.getStatus()==1){
                return "이미 존재하여 생성하지 않습니다.";
            }
        }

        requestdiary=diaryDAO.save(requestdiary);
        long memberId = requestdiary.getMember().getMemberId();
        if(requestdiary.getStatus()==0){
            return "임시 저장 완료";
        }
        String content = requestdiary.getContent();
        String prompt = getPrompt(requestdiary, content);

        DataParse response = chatGPTService.Response(prompt);
        System.out.println("response = " + response);

        saveGPTResponse(memberId,response,requestdiary);
        return "저장 완료";
    }

    private static String getPrompt(Diary requestdiary, String content) {
        String summary = requestdiary.getSummary();

        String prompt ="너는 심리학자야. \n" +
                "다음 다이어리를 읽고 분석하고 출력은 아래 응답 양식으로만 대답해.\n" +
                "\n" +
                "다이어리는 다음과 같아\n" +
                "오늘 기분 한줄 요약: "+summary + "\n" +
                "내용: "+ content +
                "\n\n" +
                "응답 양식은 예시는 다음과 같아. (- : 두 특수기호는 문자열 파싱으로 구분짓기 위한 것이므로 참고할 것.장르, 카테고리는 하나만 보여준다.)\n" +
                "분석 점수(1~10): 5\n" +
                "조언 및 인용구 한 줄: 당신도 할 수 있습니다.\n" +
                "추천 영화(영화이름,장르): 인사 이야기 - 로맨스\n" +
                "추천 음악(음악이름,가수,장르): 봄날 - 방탄소년단 - 힙합 \n" +
                "추천 음식(음식이름,메뉴카테고리(한식,양식,중식,일식,아시안) 맵기(0~3)): 된장찌개 - 한식 - 1";
        return prompt;
    }


    private int saveGPTResponse(long memberId, DataParse response, Diary diary) {
        Food food = foodService.findFoodByFoodName(response.getFood().getFoodName());
        Movie movie = movieService.findByMovieName(response.getMovie().getMovieName());
        Music music = musicService.findByMusicName(response.getMusic().getMusicName());

        if(food ==null)
            food=foodService.saveFood(response.getFood());
        diaryFoodService.saveDiaryFood(new DiaryFood(diary,food,true));
        if(movie==null)
            movie=movieService.saveMovie(response.getMovie());
        diaryMovieService.saveDiaryMovie(new DiaryMovie(diary,movie,true));
        if(music==null)
            music=musicService.saveMusic(response.getMusic());
        diaryMusicService.saveDiaryMusic(new DiaryMusic(diary,music,true));
        diaryEmotionService.saveDiaryEmotion(new DiaryEmotion(diary,response.getEmotionScore()));
        commentService.saveComment(new Comment(diary,response.getComment().getCommentContent()));

        return 0;
    }

    @Transactional
    @Override
    public String deleteDiary(int diaryId) {
        diaryFoodService.delete(diaryId);
        diaryEmotionService.delete(diaryId);
        diaryMusicService.delete(diaryId);
        diaryMovieService.delete(diaryId);
        commentService.delete(diaryId);

        diaryDAO.deleteById(diaryId);
        return "삭제 완료";
    }

    @Override
    public List<Diary> getMemberDiaries(long memberId) {
        return diaryDAO.findAllByMemberMemberId(memberId);
    }

    @Override
    public Diary findDiary(int diaryId){
        return diaryDAO.findById(diaryId).orElseThrow();
    }
}
