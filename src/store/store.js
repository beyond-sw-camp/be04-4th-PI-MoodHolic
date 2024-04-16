import { createStore } from 'vuex';

export default createStore({
    state() {
        return {
            isAuthenticated: false,
            token: null
        };
    },
    mutations: {
        setAuth(state, { isAuthenticated, token }) {
            state.isAuthenticated = isAuthenticated;
            state.token = token;
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

