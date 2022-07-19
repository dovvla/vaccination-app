<template>
  <div class="justify-content-center login">
    <b-alert v-model="showSuccessAlert" dismissible fade variant="danger">
      Bad credentials.
    </b-alert>
    <b-card title="Login">
      <b-form>
        <b-form-input
          id="input-1"
          v-model="username"
          placeholder="Username"
          required
        >
        </b-form-input>

        <b-form-input
          id="input-2"
          v-model="password"
          placeholder="Password"
          required
          type="password"
        >
        </b-form-input>
        <div class="mt-2">
          <b-button variant="primary" type="button" v-on:click="login()"
            >Login</b-button
          >
        </div>
      </b-form>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      showSuccessAlert: false,
    };
  },

  methods: {
    login() {
      var _this = this;
      if (this.username.trim() == "" && this.password.trim() == "") {
        _this.showSuccessAlert = true;
        return;
      }
      this.axios
        .post(
          "api/auth/login",
          `<?xml version="1.0" encoding="UTF-8"?>
              <jwtAuthenticationRequest>
                <email>${this.username}</email>
                <password>${this.password}</password>
              </jwtAuthenticationRequest>`,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/xml",
            },
          }
        )
        .then((response) => {
          let accessToken = response.data
            .split("<accessToken>")[1]
            .split("</accessToken>")[0];
          console.log(accessToken);
          sessionStorage.setItem("token", accessToken);
          this.findUserRole();
        })
        .catch((error) => {
          console.log(error);
          _this.showSuccessAlert = true;
        });
    },

    findUserRole() {
      var userRole = JSON.parse(
        atob(sessionStorage.getItem("token").split(".")[1])
      ).role[0].authority;
      console.log(userRole);
      if (userRole == "Sluzbenik") {
        this.$router.push("SluzbenikPage");
      }
      if (userRole == "Gradjanin") {
        this.$router.push("GradjaninPage");
      }
      if (userRole == "Zdravstveni_radnik") {
        this.$router.push("/medicinski-radnik-home");
      }
    },
  },
};
</script>

<style scoped>
.login {
  max-width: 40rem;
  background-color: #ffffff;
  margin: auto;
  margin-top: 100px;
  margin-bottom: 200px;
  padding: 20px;
}
</style>
