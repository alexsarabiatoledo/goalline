import { createStore } from 'vuex';

export default createStore({
  state: {
    isLoggedIn: false,
    virtualAssistantVisible: true,
  },
  mutations: {
    login(state) {
      state.isLoggedIn = true;
    },
    logout(state) {
        sessionStorage.removeItem('token');
        state.isLoggedIn = false;
    },
    setVirtualAssistantVisible(state, visible) {
      state.virtualAssistantVisible = visible;
    },
  },
});
