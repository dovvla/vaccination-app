<template>
  <div>
    <b-navbar fixed="top" toggleable="lg" type="light" variant="light">
      <b-navbar-brand href="/GradjaninPage">Home Page</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="/GradjaninPage/interesovanje"
            >Interesovanje</b-nav-item
          >
          <b-nav-item href="/GradjaninPage/saglasnost">Saglasnost</b-nav-item>
          <b-nav-item href="/GradjaninPage/zahtev"
            >Zahtevi za zeleni sertifikat</b-nav-item
          >
          <b-nav-item href="/GradjaninPage/moji-dokumenti"
            >Moji dokumenti</b-nav-item
          >
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item right href="/Logout">Log out</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <router-view> </router-view>
  </div>
</template>
