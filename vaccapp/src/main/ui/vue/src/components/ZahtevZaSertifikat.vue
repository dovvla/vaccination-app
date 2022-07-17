<template>
  <div>
    <h2>Zahtev za zeleni sertifikat</h2>
    <b-form>
      <br />
      <b-form-input
        id="razlog"
        name="razlog"
        placeholder="razlog"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="razlog"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-input
        id="mesto"
        name="mesto"
        placeholder="mesto"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="mesto"
      ></b-form-input>
      <b-button @click="makeZahtev" class="mb-2 mr-sm-2 mb-sm-0"
        >Napravi zahtev</b-button
      >
    </b-form>

    <b-modal ref="error-modal" hide-footer title="Error">
      <div class="d-block text-center">
        <p>{{ this.errorMessage }}</p>
      </div>
      <b-button
        class="mt-3"
        variant="outline-danger"
        block
        @click="hideErrorModal"
        >Close</b-button
      >
    </b-modal>

    <b-modal ref="success-modal" hide-footer title="Success">
      <div class="d-block text-center">
        <p>Request successfully submitted.</p>
      </div>
      <b-button
        class="mt-3"
        variant="outline-success"
        block
        @click="hideSuccessModal"
        >Close</b-button
      >
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "ZahtevZaSertifikat",
  data() {
    return {
      razlog: "",
      mesto: "",
      errorMessage: "",
    };
  },

  methods: {
    makeZahtev() {
      let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
<Zahtev xmlns="http://tim.robot/zahtev_za_sertifikat">
	<Identifikator xmlns="http://tim.robot/zahtev_za_sertifikat">string</Identifikator>
	<Podaci_o_podnosiocu>
	</Podaci_o_podnosiocu>
	<Razlog_za_podnosenje xmlns="http://tim.robot/zahtev_za_sertifikat">${this.razlog}</Razlog_za_podnosenje>
	<Mesto xmlns="http://tim.robot/zahtev_za_sertifikat">${this.mesto}</Mesto>
    <Status>Neobradjen</Status>
	<Razlog_za_odbijanje>
	</Razlog_za_odbijanje>
</Zahtev>`;
      this.axios
        .post(`/api/zahtev-za-sertifikat`, xmlString, {
          headers: {
            "Content-Type": "application/xml",
            Authorization: "Bearer " + sessionStorage.getItem("token"),
          },
        })
        .then(() => {
          this.showSuccessModal();
        })
        .catch((error) => {
          this.errorMessage = error.response.data;
          this.showErrorModal();
        });
      this.$forceUpdate();
    },
    hideErrorModal() {
      this.$refs["error-modal"].hide();
    },
    hideSuccessModal() {
      this.$refs["success-modal"].hide();
    },
    showErrorModal() {
      this.$refs["error-modal"].show();
    },
    showSuccessModal() {
      this.$refs["success-modal"].show();
    },
  },
};
</script>

<style></style>
