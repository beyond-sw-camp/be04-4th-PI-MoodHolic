import { createStore } from 'vuex';

export default createStore({
    state() {
        return {
            isAuthenticated: false,
            token: null,
            memberId: null,
            nickname: null,
        };
    },
    mutations: {
        setAuth(state, { isAuthenticated, token }) {
            state.isAuthenticated = isAuthenticated;
            state.token = token;
        },
        setGlobalVariable(state, newValue) {
            state.memberId = newValue;
        },
        setNickname(state, newValue) {
            state.nickname = newValue;
        }
    },
    actions: {
        login({ commit }, token) {
            commit('setAuth', { isAuthenticated: true, token });
            localStorage.setItem('authToken', token);
            // location.reload(true);

        },
        logout({ commit }) {
            commit('setAuth', { isAuthenticated: false, token: null });
            localStorage.removeItem('authToken');
            location.reload(true);
        },
        checkAuth({ commit }) {
            const token = localStorage.getItem('authToken');
            if (token) {
                commit('setAuth', { isAuthenticated: true, token });
            } else {
                commit('setAuth', { isAuthenticated: false, token: null });
            }
        },
        initializeAuth({ dispatch }) {
            dispatch('checkAuth');
        },

        updateMemberId({ commit }, newValue) {
            commit('setGlobalVariable', newValue); // 여기 수정
        },
        updateNickname({ commit }, newValue) {
            commit('setNickname', newValue); // 여기 수정
        }
    },
    getters: {
        isAuthenticated(state) {
            return state.isAuthenticated;
        },
        token(state) {
            return state.token;
        }
    }
});

