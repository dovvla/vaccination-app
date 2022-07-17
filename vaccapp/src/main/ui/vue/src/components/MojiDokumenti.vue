<template>
  <div>
    <h1>Moja dokumenta</h1>
    <b-row style="margin: 20px">
      <b-col>
        <b-button @click="pregledajInteresovanje"
          >Pregledaj interesovanje</b-button
        >
      </b-col>
      <b-col> <b-button>Preuzmi interesovanje kao pdf</b-button> </b-col
      ><b-col>
        <b-button>Preuzmi interesovanje kao xhtml</b-button>
      </b-col>
      <b-col>
        <b-button @click="pregledajSaglasnost">Pregledaj saglasnost</b-button>
      </b-col>
      <b-col> <b-button>Preuzmi saglasnost kao pdf</b-button> </b-col
      ><b-col>
        <b-button>Preuzmi saglasnost kao xhtml</b-button>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <div>
          <div
            style="margin: 20px"
            v-for="(zahtev, index) in zahtevi"
            :key="index"
          >
            <b-button style="margin: 0 10px" @click="pregledajZahtev(zahtev)"
              >Pregledaj zahtev {{ index + 1 }}</b-button
            >
            <b-button style="margin: 0 10px" @click="preuzmiPdfZahtev(zahtev)"
              >Preuzmi zahtev {{ index + 1 }} kao pdf</b-button
            >
            <b-button style="margin: 0 10px" @click="preuzmiHtmlZahtev(zahtev)"
              >Preuzmi zahtev {{ index + 1 }} kao xhtml</b-button
            >
          </div>
        </div>
      </b-col>
      <b-col>
        <iframe
          id="fred"
          style="border: 1px solid #666ccc"
          :src="trenutniDokument"
          frameborder="1"
          scrolling="auto"
          height="1100"
          width="850"
        ></iframe>
      </b-col>
    </b-row>
  </div>
</template>
<script>
export default {
  name: "MojiDokumenti",
  data() {
    return {
      trenutniDokument: "",
      me: {},
      jmbg: "",
      zahtevi: [],
    };
  },
  methods: {
    pregledajSaglasnost() {
      this.trenutniDokument = `/documents/saglasnost_${this.jmbg}.pdf`;
    },
    pregledajInteresovanje() {
      this.trenutniDokument = `/documents/interesovanja_${this.jmbg}.pdf`;
    },
    pregledajZahtev(zahtev) {
      this.trenutniDokument = `/documents/zahtev-zeleni_${zahtev}.pdf`;
    },
    preuzmiPdfZahtev(zahtev) {
      //   this.trenutniDokument = `/documents/zahtev-zeleni_${zahtev}.pdf`;
    },
    preuzmiHtmlZahtev(zahtev) {
      //   this.trenutniDokument = `/documents/zahtev-zeleni_${zahtev}.pdf`;
    },
  },
  mounted() {
    const xml = require("txml");

    this.axios
      .get(`/api/korisnici/me`, {
        headers: {
          Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
      })
      .then((response) => {
        const json = xml.parse(response.data);
        this.me = json;
        console.log(this.me);
        this.jmbg = this.me[1].children[1].children[0];
        console.log(this.jmbg);
        this.axios
          .get(`/api/zahtev-za-sertifikat/user/${this.jmbg}`, {
            headers: {
              Authorization: "Bearer " + sessionStorage.getItem("token"),
            },
          })
          .then((response) => {
            const json = xml.parse(response.data);
            this.zahtevi = json[1].children.map(
              (child) => child.children[0].children[0]
            );
            console.log(this.zahtevi);
          })
          .catch((error) => {
            this.errorMessage = error.response.data;
            this.showErrorModal();
          });
      })
      .catch((error) => {
        this.errorMessage = error.response.data;
        this.showErrorModal();
      });
  },
};
</script>

<style></style>
