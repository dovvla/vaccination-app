<template>
  <div>
    <h2>Interesovanje</h2>
    <b-form>
      <b-form-select
        id="drzavljanstvo"
        name="drzavljanstvo"
        placeholder="Drzavljnastvo"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="drzavljanstvo"
        :options="drzavljanstvoOptions"
      >
      </b-form-select>
      <br />
      <br />
      <b-form-input
        id="jmbg"
        name="jmbg"
        placeholder="JMBG"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="jmbg"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="ime"
        name="ime"
        placeholder="Ime"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="ime"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="prezime"
        name="prezime"
        placeholder="Prezime"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="prezime"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="mesto"
        name="mesto"
        placeholder="Mesto"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="mesto"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="fiksniTelefon"
        name="fiksniTelefon"
        placeholder="Fiksni Telefon"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="fiksniTelefon"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="mobilniTelefon"
        name="mobilniTelefon"
        placeholder="Mobilni Telefon"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="mobilniTelefon"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="email"
        name="email"
        placeholder="Email"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="email"
      >
      </b-form-input>
      <br />
      <b-form-select
        id="zeljenaVakcina"
        name="zeljenaVakcina"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="zeljenaVakcina"
        :options="zeljenaVakcinaOptions"
      >
      </b-form-select>
      <br />
      <br />
      <label>Davalac krvi:</label>
      <b-form-select
        id="davalacKrvi"
        name="davalacKrvi"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="davalacKrvi"
        :options="davalacKrviOptions"
      >
      </b-form-select>
      <br />
      <b-button @click="makeInteresovanje" class="mb-2 mr-sm-2 mb-sm-0"
        >Interesovanje</b-button
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
  name: "Interesovanje",
  data() {
    return {
      drzavljanstvo: "RS",
      jmbg: "",
      brojPasosa: "",
      ime: "",
      prezime: "",
      imeRoditelja: "",
      pol: "Muski",
      datumRodjenja: new Date().toISOString(),
      mestoRodjenja: "",
      adresa: "",
      mesto: "",
      fiksniTelefon: "",
      mobilniTelefon: "",
      email: "",
      password: "",
      polOptions: ["Muski", "Zenski"],
      zeljenaVakcinaOptions: [
        "Pfizer",
        "Sputnik",
        "Sinopharm",
        "AZ",
        "Moderna",
        "Bilo koja",
      ],
      davalacKrviOptions: ["Da", "Ne"],
      davalacKrvi: "Ne",
      errorMessage: "",
      zeljenaVakcina: "Sinopharm",
      drzavljanstvoOptions: ["RS", "Strani sa boravkom", "Strani bez boravka"],
    };
  },

  methods: {
    makeInteresovanje() {
      this.errorMessage = "";
      let reemail = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
      let reFiksni = /0\d{8,9}/;
      let reMobilni = /06\d{7,8}/;
      if (this.ime === "") {
        this.errorMessage = "Ime je prazno";
        this.showErrorModal();
      }
      if (this.prezime === "") {
        this.errorMessage = "Prezime je prazno";
        this.showErrorModal();
      }
      if (!reemail.test(this.email)) {
        this.errorMessage = "Neispravan email";
        this.showErrorModal();
      }
      if (!reFiksni.test(this.fiksniTelefon)) {
        this.errorMessage = "Neispravan fiksni";
        this.showErrorModal();
      }
      if (!reMobilni.test(this.mobilniTelefon)) {
        this.errorMessage = "Neispravan mobilni";
        this.showErrorModal();
      }
      if (this.jmbg.length !== 13) {
        {
          this.errorMessage = "JMBG mora imati 13 karaktera";
          this.showErrorModal();
        }
      }
      if (this.errorMessage !== "") {
        return;
      }

      let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
<Obrazac_interesovanja xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">
	<Licni_podaci>
		<Drzavljanstvo xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.drzavljanstvo
    }</Drzavljanstvo>
		<JMBG xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.jmbg
    }</JMBG>
		<Ime xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.ime
    }</Ime>
		<Prezime xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.prezime
    }</Prezime>
		<Imejl xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.email
    }</Imejl>
		<Broj_mobilnog_telefona xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.mobilniTelefon
    }</Broj_mobilnog_telefona>
		<Broj_fiksnog_telefona xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
      this.fiksniTelefon
    }</Broj_fiksnog_telefona>
	</Licni_podaci>
	<Zeljena_lokacija_vakcinacije xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
    this.mesto
  }</Zeljena_lokacija_vakcinacije>
	<Zeljena_vakcina xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
    this.zeljenaVakcina
  }</Zeljena_vakcina>
	<Davalac_krvi xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${
    this.davalacKrvi
  }</Davalac_krvi>
	<Datum xmlns="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju">${new Date().toISOString()}</Datum>
</Obrazac_interesovanja>`;
      this.axios
        .post(`/api/interesovanje`, xmlString, {
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
