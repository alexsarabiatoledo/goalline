import { createStore } from 'vuex';

export default createStore({
  state: {
    userRole: '',
    isLoggedIn: false,
    selectedStudents: [],
  },
  mutations: {
    setUserRole(state, role) {
        state.userRole = role;
    },
    setLoggedIn(state, value) {
        state.isLoggedIn = value;
    },
    updateSelectedStudents(state, students) {
      state.selectedStudents = students;
    },
  },
  actions: {
    updateUserRole({ commit }, email) {
      let role = '';

      role = email;

      commit('setUserRole', role);
    },
    updateLoginStatus({ commit }, value) {
        commit('setLoggedIn', value);
      },
    }

});