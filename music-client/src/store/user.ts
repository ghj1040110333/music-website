export default {
  state: {
    userId: "1", // ID
    username: "李云龙", // 名字
    userPic: "", // 图片
  },
  getters: {
    userId: (state) => state.userId,
    username: (state) => state.username,
    userPic: (state) => state.userPic,
  },
  mutations: {
    setUserId: (state, userId) => {
      state.userId = userId;
    },
    setUsername: (state, username) => {
      state.username = username;
    },
    setUserPic: (state, userPic) => {
      state.userPic = userPic;
    },
  },
};
