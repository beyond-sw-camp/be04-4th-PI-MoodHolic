package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.*;
import akatsuki.moodholic.dto.ResponseDiary;
import akatsuki.moodholic.etc.DataParse;
import akatsuki.moodholic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiaryService {
    private MemberDAO memberDAO;
    private DiaryDAO diaryDAO;
    private EmotionDAO emotionDAO;
    private FoodDAO foodDAO;
    private MovieDAO movieDAO;
    private MusicDAO musicDAO;
    private DiaryEmotionDAO diaryEmotionDAO;
    private DiaryFoodDAO diaryFoodDAO;
    private DiaryMovieDAO diaryMovieDAO;
    private DiaryMusicDAO diaryMusicDAO;
    private CommentDAO commentDAO;
    private ChatGPTService chatGPTService;


    @Autowired
    public DiaryService(MemberDAO memberDAO, DiaryDAO diaryDAO, EmotionDAO emotionDAO, FoodDAO foodDAO, MovieDAO movieDAO, MusicDAO musicDAO, DiaryEmotionDAO diaryEmotionDAO, DiaryFoodDAO diaryFoodDAO, DiaryMovieDAO diaryMovieDAO, DiaryMusicDAO diaryMusicDAO, ChatGPTService chatGPTService,CommentDAO commentDAO) {
        this.memberDAO = memberDAO;
        this.diaryDAO = diaryDAO;
        this.emotionDAO = emotionDAO;
        this.foodDAO = foodDAO;
        this.movieDAO = movieDAO;
        this.musicDAO = musicDAO;
        this.diaryEmotionDAO = diaryEmotionDAO;
        this.diaryFoodDAO = diaryFoodDAO;
        this.diaryMovieDAO = diaryMovieDAO;
        this.diaryMusicDAO = diaryMusicDAO;
        this.chatGPTService = chatGPTService;
        this.commentDAO = commentDAO;
    }

    public ResponseDiary getDiary(int diaryId) {
        Diary diary = diaryDAO.findById(diaryId).orElseThrow();
        // emotion, food, movie, music,
        DiaryFood food= diaryFoodDAO.findByDiaryId(diaryId);
        DiaryEmotion emotion = diaryEmotionDAO.findByDiaryIdDiaryId(diaryId);
        DiaryMovie movie = diaryMovieDAO.findByDiaryId(diaryId);
        DiaryMusic music = diaryMusicDAO.findByDiaryId(diaryId);
        Comment comment = commentDAO.findByDiaryId(diaryId);
//        System.out.println("diary = " + diary);
//        System.out.println("food = " + food.getFoodId());
//        System.out.println("emotion = " + emotion.getEmotionId());
//        System.out.println("music = " + music.getMusicId());
//        System.out.println("movie = " + movie.getMovieId());
        String emotionValue= new String();

        if(emotion !=null) {
            if (emotion.getEmotionId() < 4)  emotionValue= "나쁨";
            else if(emotion.getEmotionId()<7) emotionValue="보통";
            else emotionValue="좋음";
        }
        return new ResponseDiary(diary,emotionValue,food.getFoodId(),movie.getMovieId(),music.getMusicId());
    }

    @Transactional
    public String postDiary(Diary requestdiary) {
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
        Food food =foodDAO.findByFoodName(response.getFood().getFoodName());
        Movie movie = movieDAO.findByMovieName(response.getMovie().getMovieName());
        Music music = musicDAO.findByMusicName(response.getMusic().getMusicName());


        if(food ==null)
            food= foodDAO.save(response.getFood());
        diaryFoodDAO.save(new DiaryFood(diary,food,true));
        if(movie==null)
            movie = movieDAO.save(response.getMovie());
        diaryMovieDAO.save(new DiaryMovie(diary,movie,true));
        if(music==null)
            music= musicDAO.save(response.getMusic());
        diaryMusicDAO.save(new DiaryMusic(diary,music,true));
        diaryEmotionDAO.save(new DiaryEmotion(diary,response.getEmotionScore()));
        commentDAO.save(new Comment(diary,response.getComment().getCommentContent()));

        return 0;
    }

    @Transactional
    public String deleteDiary(int diaryId) {
            diaryFoodDAO.deleteByDiaryId(diaryId);
            diaryEmotionDAO.deleteByDiaryIdDiaryId(diaryId);
            diaryMusicDAO.deleteByDiaryId(diaryId);
            diaryMovieDAO.deleteByDiaryId(diaryId);
            commentDAO.deleteByDiaryId(diaryId);
            diaryDAO.deleteById(diaryId);
        return "삭제 완료";
    }

    public List<Diary> getMemberDiaries(long memberId) {
        return diaryDAO.findAllByMemberMemberId(memberId);
    }
}
