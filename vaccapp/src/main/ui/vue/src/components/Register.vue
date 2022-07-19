<template>
  <div>
    <h2>Register</h2>
    <b-form>
      <b-form-input
        id="drzavljanstvo"
        name="drzavljanstvo"
        placeholder="Drzavljnastvo"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="drzavljanstvo"
      >
      </b-form-input>
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
        id="brojPasosa"
        name="brojPasosa"
        placeholder="Broj Pasosa"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="brojPasosa"
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
        id="imeRoditelja"
        name="imeRoditelja"
        placeholder="Ime Roditelja"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="imeRoditelja"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="datumRodjenja"
        name="datumRodjenja"
        type="date"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="datumRodjenja"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="mestoRodjenja"
        name="mestoRodjenja"
        placeholder="Mesto Rodjenja"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="mestoRodjenja"
      >
      </b-form-input>
      <br />
      <b-form-input
        id="adresa"
        name="adresa"
        placeholder="Adresa"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="adresa"
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
      <b-form-input
        id="password"
        name="password"
        placeholder="password"
        type="password"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="password"
      >
      </b-form-input>
      <br />
      <b-form-select
        id="pol"
        name="pol"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="pol"
        :options="polOptions"
      >
      </b-form-select>
      <br />
      <br />
      <b-button @click="registerUser" class="mb-2 mr-sm-2 mb-sm-0"
        >Create request</b-button
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
  name: "Register",
  data() {
    return {
      drzavljanstvo: "",
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
      errorMessage: "",
    };
  },

  methods: {
    registerUser() {
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
      if (this.jmbg.length !== 13) {
        {
          this.errorMessage = "JMBG mora imati 13 karaktera";
          this.showErrorModal();
        }
      }
      if (!reFiksni.test(this.fiksniTelefon)) {
        this.errorMessage = "Neispravan fiksni";
        this.showErrorModal();
      }
      if (!reMobilni.test(this.mobilniTelefon)) {
        this.errorMessage = "Neispravan mobilni";
        this.showErrorModal();
      }
      if (this.errorMessage !== "") {
        return;
      }

      let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                <Korisnik xmlns="http://tim.robot/korisnik">
                    <Drzavljanstvo xmlns="http://tim.robot/korisnik">${
                      this.drzavljanstvo
                    }</Drzavljanstvo>
                    <Jmbg xmlns="http://tim.robot/korisnik">${this.jmbg}</Jmbg>
                    <Broj_pasosa xmlns="http://tim.robot/korisnik">${
                      this.brojPasosa
                    }</Broj_pasosa>
                    <Ime xmlns="http://tim.robot/korisnik">${this.ime}</Ime>
                    <Prezime xmlns="http://tim.robot/korisnik">${
                      this.prezime
                    }</Prezime>
                    <Ime_roditelja xmlns="http://tim.robot/korisnik">${
                      this.imeRoditelja
                    }</Ime_roditelja>
                    <Pol xmlns="http://tim.robot/korisnik">${this.pol}</Pol>
                    <Datum_rodjenja xmlns="http://tim.robot/korisnik">${new Date(
                      this.datumRodjenja
                    ).toISOString()}</Datum_rodjenja>
                    <Mesto_rodjenja xmlns="http://tim.robot/korisnik">${
                      this.mestoRodjenja
                    }</Mesto_rodjenja>
                    <Adresa xmlns="http://tim.robot/korisnik">${
                      this.adresa
                    }</Adresa>
                    <Mesto xmlns="http://tim.robot/korisnik">${
                      this.mesto
                    }</Mesto>
                    <Grad xmlns="http://tim.robot/korisnik">${this.grad}</Grad>
                    <Fiksni_telefon xmlns="http://tim.robot/korisnik">${
                      this.fiksniTelefon
                    }</Fiksni_telefon>
                    <Mobilni_telefon xmlns="http://tim.robot/korisnik">${
                      this.mobilniTelefon
                    }</Mobilni_telefon>
                    <Email xmlns="http://tim.robot/korisnik">${
                      this.email
                    }</Email>
                    <Sifra xmlns="http://tim.robot/korisnik">${
                      this.password
                    }</Sifra>
                    <Rola xmlns="http://tim.robot/korisnik">Gradjanin</Rola>
                </Korisnik>`;
      this.axios
        .post(`/api/korisnici/register`, xmlString, {
          headers: { "Content-Type": "application/xml" },
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
