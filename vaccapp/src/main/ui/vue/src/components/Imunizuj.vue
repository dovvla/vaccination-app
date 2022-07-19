<template>
  <div>
    <h1>Moja dokumenta</h1>
    <b-row style="margin: 20px">
      <b-col>
        <b-button @click="pregledajSaglasnost"
          >Pregledaj interesovanje</b-button
        >
      </b-col>
      <b-col>
        <button @click="preuzmiPdfInteresovanje">
          Preuzmi interesovanje kao pdf
        </button> </b-col
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
            <b-button style="margin: 0 10px"
              >Preuzmi zahtev {{ index + 1 }} kao pdf</b-button
            >
            <b-button style="margin: 0 10px"
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
    preuzmiPdfInteresovanje() {
      axios
        .get(
          `/api/pdf/printing/?file=interesovanja_${this.jmbg}.pdf`,
          { responseType: "blob" } // !!!
        )
        .then((response) => {
          window.open(URL.createObjectURL(response.data));
        });
    },
    pregledajZahtev(zahtev) {
      this.trenutniDokument = `/documents/zahtev-zeleni_${zahtev}.pdf`;
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
        this.jmbg = this.me[1].children[1].children[0];
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
