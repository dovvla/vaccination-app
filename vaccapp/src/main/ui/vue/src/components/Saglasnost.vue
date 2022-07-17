<template>
  <div>
    <h2>Saglasnost</h2>
    <b-form>
      <br />
      <b-form-input
        id="ulica"
        name="ulica"
        placeholder="Ulica"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="ulica"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-input
        id="broj"
        name="broj"
        placeholder="Broj"
        type="number"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="broj"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-input
        id="naselje"
        name="naselje"
        placeholder="Naselje"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="naselje"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-input
        id="grad"
        name="grad"
        placeholder="Grad"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="grad"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-select
        id="radniStatus"
        name="radniStatus"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="radniStatus"
        :options="radniStatusOptions"
      >
      </b-form-select>
      <br />
      <br />
      <b-form-select
        id="zanimanjeZaposlenog"
        name="zanimanjeZaposlenog"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="zanimanjeZaposlenog"
        :options="zanimanjeZaposlenogOptions"
      >
      </b-form-select>
      <br />
      <br />
      <label>Korisnik socijalne zastite</label>
      <b-form-select
        id="korisnikSocijalneZastite"
        name="korisnikSocijalneZastite"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="korisnikSocijalneZastite"
        :options="korisnikSocijalneZastiteOptions"
      >
      </b-form-select>
      <br />
      <br />
      <b-form-input
        id="sedisteNaziv"
        name="sedisteNaziv"
        placeholder="Naziv sedista"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="sedisteNaziv"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-input
        id="sedisteOpstina"
        name="sedisteOpstina"
        placeholder="Naziv Opstine"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="sedisteOpstina"
      >
      </b-form-input>
      <br />
      <br />
      <b-form-select
        id="saglasnost"
        name="saglasnost"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="saglasnost"
        :options="saglasnostOptions"
      >
      </b-form-select>
      <br />
      <br />
      <b-form-input
        id="imunoloskiLek"
        name="imunoloskiLek"
        placeholder="Imunoloski lek"
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="imunoloskiLek"
      >
      </b-form-input>
      <b-button @click="makeSaglasnost" class="mb-2 mr-sm-2 mb-sm-0"
        >Saglasnost</b-button
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
  name: "Saglasnost",
  data() {
    return {
      ulica: "",
      broj: 1,
      naselje: "",
      grad: "",
      radniStatus: "Zaposlen",
      radniStatusOptions: [
        "Zaposlen",
        "Nezaposlen",
        "Penzioner",
        "Ucenik",
        "Student",
        "Dete",
      ],
      zanimanjeZaposlenog: "MUP",
      zanimanjeZaposlenogOptions: [
        "Zdravstvena zastita",
        "Socijalna zastita",
        "Prosveta",
        "MUP",
        "Vojska RS",
        "Drugo",
      ],
      korisnikSocijalneZastite: "DA",
      korisnikSocijalneZastiteOptions: ["DA", "NE"],
      sedisteNaziv: "",
      sedisteOpstina: "",
      saglasnost: "SAGLASAN SAM",
      saglasnostOptions: ["SAGLASAN SAM", "NISAM SAGLASAN"],
      imunoloskiLek: "",
      errorMessage: "",
    };
  },
  methods: {
    makeSaglasnost() {
      this.errorMessage = "";

      let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
<Obrazac about="string" href="string" rel="string" xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
	<Podaci_o_pacijentu xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
    <Drzavljanstvo></Drzavljanstvo>
    <Ime></Ime>
    <Prezime></Prezime>
		<Adresa xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
			<Ulica>${this.ulica}</Ulica>
			<Broj>${this.broj}</Broj>
		</Adresa>
		<Naselje xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.naselje
    }</Naselje>
		<Grad xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.grad
    }</Grad>
		<Radni_status xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.radniStatus
    }</Radni_status>
		<Zanimanje_zaposlenog xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.zanimanjeZaposlenog
    }</Zanimanje_zaposlenog>
		<Korisnik_socijalne_zastite xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.korisnikSocijalneZastite
    }</Korisnik_socijalne_zastite>
	</Podaci_o_pacijentu>
	<Podaci_o_saglasnosti xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
		<Saglasnost xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.saglasnost
    }</Saglasnost>
		<Naziv_imunoloskog_leka xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">${
      this.imunoloskiLek
    }</Naziv_imunoloskog_leka>
		<Datum property="string" datatype="string" xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
			<value>${new Date().toISOString()}</value>
		</Datum>
	</Podaci_o_saglasnosti>
</Obrazac>`;
      this.axios
        .post(`/api/saglasnost`, xmlString, {
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
