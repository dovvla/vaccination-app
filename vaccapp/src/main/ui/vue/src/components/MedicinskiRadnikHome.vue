<template>
<div>
    <b-row>
        <b-col>
            <b-row style="margin-left: 20px;">
                <p style="text-align: left; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Unesite JMBG pacijenta:</p>
                <b-form-input
                    id="jmbg"
                    name="jmbg"
                    placeholder="JMBG"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    style="width: 50%;"
                    v-model="jmbg"
                >
                </b-form-input>
                <b-button style="margin-bottom: 20px;" @click="pretraziSaglasnosti">
                    Pretrazi saglasnosti
                </b-button>
            </b-row>
            <b-row style="margin-left: 40px;">
                <iframe
                id="fred"
                style="border: 1px solid #666ccc"
                frameborder="1"
                scrolling="auto"
                height="1100"
                width="850"
                :src="trenutniDokument"
                ></iframe>
            </b-row>
        </b-col>
        <b-col v-if="trenutniDokument">
            <b-row>
                <b-col>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Evidencija vakcinacije:</p>
                    </b-row>
                </b-col>
                <b-col>
                    <b-row style="margin-left: -20px; margin-top: 10px;">
                        <b-button style="margin-bottom: 20px;" @click="napraviPotvrdu">
                            Zavrsi evidenciju i napravi potvrdu
                        </b-button>
                    </b-row>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Zdravstvena ustanova:</p>
                    </b-row>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Vakcinacijski punkt:</p>
                    </b-row>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Vase ime:</p>
                    </b-row>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Vase prezime:</p>
                    </b-row>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Vas faksimil:</p>
                    </b-row>
                    <b-row style="margin-left: 20px; margin-top: 10px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Vas broj telefona:</p>
                    </b-row>
                </b-col>
                <b-col>
                    <b-row style="margin-left: -20px; margin-top: 20px;">
                        <b-form-input
                            id="zdravstvenaUstanova"
                            name="zdravstvenaUstanova"
                            placeholder="Zdravstvena ustanova"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="width: 50%;"
                            v-model="zdravstvenaUstanova"
                        />
                    </b-row>
                    <b-row style="margin-left: -20px; margin-top: 20px;">
                        <b-form-input
                            id="vakcinacijskiPunkt"
                            name="vakcinacijskiPunkt"
                            placeholder="Vakcinacijski punkt"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="width: 50%;"
                            v-model="vakcinacijskiPunkt"
                        />
                    </b-row>
                    <b-row style="margin-left: -20px; margin-top: 20px;">
                        <b-form-input
                            id="lekarIme"
                            name="lekarIme"
                            placeholder="Vase ime"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="width: 50%;"
                            v-model="lekarIme"
                            readonly
                        />
                    </b-row>
                    <b-row style="margin-left: -20px; margin-top: 20px;">
                        <b-form-input
                            id="lekarPrezime"
                            name="lekarPrezime"
                            placeholder="Vase prezime"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="width: 50%;"
                            v-model="lekarPrezime"
                            readonly
                        />
                    </b-row>
                    <b-row style="margin-left: -20px; margin-top: 20px;">
                        <b-form-input
                            id="lekarFaksimil"
                            name="lekarFaksimil"
                            placeholder="Vas faksimil"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="width: 50%;"
                            v-model="lekarFaksimil"
                        />
                    </b-row>
                    <b-row style="margin-left: -20px; margin-top: 20px;">
                        <b-form-input
                            id="lekarBrojTelefona"
                            name="lekarBrojTelefona"
                            placeholder="Vas broj telefona"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="width: 50%;"
                            v-model="lekarBrojTelefona"
                        />
                    </b-row>
                </b-col>          
            </b-row>
            <b-row>
                <b-col>
                    <b-row style="margin-left: 20px;">
                        <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px;">Evidentirane vakcinacije:</p>
                    </b-row>
                    <div
                        style="margin-left: 20px;margin-right: 20px; margin-top: 10px;"
                        v-for="(primljenaVakcina, index) in primljeneVakcine"
                        :key="index"
                    >
                        <b-card bg-variant="light" align-self="center">
                            
                            <b-row>
                                <p style="text-align: center; font-family: sans-serif; font-size: medium; padding: 5px;">{{"Naziv: " + primljenaVakcina.naziv}}</p>
                            </b-row>
                            <b-row>
                                <p style="text-align: center; font-family: sans-serif; font-size: medium; padding: 5px;">{{"Ekstremitet: " + primljenaVakcina.ekstremitet}}</p>
                            </b-row>
                            <b-row>
                                <p style="text-align: center; font-family: sans-serif; font-size: medium; padding: 5px;">{{"Serija: " + primljenaVakcina.serijaVakcine}}</p>
                            </b-row>
                            <b-row>
                                <p style="text-align: center; font-family: sans-serif; font-size: medium; padding: 5px;">{{"Proizvodjac: " + primljenaVakcina.proizvodjac}}</p>
                            </b-row>
                            
                            <b-row>
                                <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: medium; padding: 5px; margin-left: 12px;">Nezeljena reakcija:</p>
                                <p style="text-align: center; font-family: sans-serif; font-size: medium; padding: 5px;">{{primljenaVakcina.nezeljenaReakcija}}</p>
                            </b-row>
                        </b-card>
                    </div>
                </b-col>
                <b-col>
                    <div>
                        <b-card bg-variant="light" align-self="center" style="width: 50%">
                        <b-form-input
                            id="novaNaziv"
                            name="novaNaziv"
                            placeholder="Naziv nove doze"
                            class="mb-2 mr-sm-2 mb-sm-0"   
                            style="margin-top: 10px;"                        
                            v-model="novaNaziv"
                        />
                        <b-form-input
                            id="novaEkstremitet"
                            name="novaEkstremitet"
                            placeholder="Novi ekstremitet"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="margin-top: 10px;"  
                            v-model="novaEkstremitet"
                        />
                        <b-form-input
                            id="novaSerija"
                            name="novaSerija"
                            placeholder="Nova serija"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="margin-top: 10px;"  
                            v-model="novaSerija"
                        />
                        <b-form-input
                            id="novaProizvodjac"
                            name="novaProizvodjac"
                            placeholder="Novi proizvodjac"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="margin-top: 10px;"  
                            v-model="novaProizvodjac"
                        />
                        <b-form-input
                            id="novaNezeljena"
                            name="novaNezeljena"
                            placeholder="Nezeljene reakcije"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            style="margin-top: 10px;"  
                            v-model="novaNezeljena"
                        />
                        <b-button style="margin-top: 20px;" @click="dodajVakcinacija">
                            Dodaj vakcinaciju
                        </b-button>
                        </b-card>
                    </div>
                </b-col>
            </b-row>
        </b-col>
    </b-row>
</div>
</template>
<script>
export default {
  name: "MedicinskiRadnikHome",
  data(){
    return {
        trenutniDokument: "",
        jmbg: "",
        saglasnosti: [],

        drzavljanstvo: "",
        prezime: "",
        ime: "",
        ime_roditelja: "",
        datumRodjenja: "",
        mestoRodjenja: "",
        pol: "",
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

        zdravstvenaUstanova: "",
        vakcinacijskiPunkt: "",
        lekarIme:"",
        lekarPrezime:"",
        lekarFaksimil:"",
        lekarBrojTelefona: "",
        primljeneVakcine : [],

        novaNaziv: "",
        novaEkstremitet: "",
        novaSerija: "",
        novaProizvodjac: "",
        novaNezeljena: "",

        errorMessage: "",
    };
  },
  methods: {
    pretraziSaglasnosti() {
        const xml = require("txml");
        this.axios.get(`/api/saglasnost/user/${this.jmbg}`, {
        headers: {
          Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        })
        .then((response) => {
            const json = xml.parse(response.data);
            //console.log(json);
            this.saglasnosti = json[1].children.map(
              (child) => child.children[0]
            );
            let curr = this.saglasnosti[this.saglasnosti.length - 1];
            console.log(curr);
            this.drzavljanstvo = curr.children[0].children[0].children[0];
            console.log(this.drzavljanstvo);
            this.prezime = curr.children[1].children[0];
            this.ime = curr.children[2].children[0];
            //this.ime_roditelja = curr.children[3].children[0];
            this.pol = curr.children[4].children[0];
            console.log(this.ime);
            //this.datumRodjenja = curr.children[5].children[0];
            //this.mestoRodjenja = curr.children[6].children[0];
            this.trenutniDokument = `/documents/saglasnost_${this.drzavljanstvo}.pdf`;
        })
        .catch((error) => {
                this.errorMessage = error.response.data;
                this.showErrorModal();
        });
    },
    dodajVakcinacija(){
        if(this.novaNaziv === ""){
            this.errorMessage = "Naziv nove vakcinacije je prazan!";
            this.showErrorModal();
        }
        if(this.novaEkstremitet === ""){
            this.errorMessage = "Ekstremitet nove vakcinacije je prazan!";
            this.showErrorModal();
        }
        if(this.novaSerija === ""){
            this.errorMessage = "Serija nove vakcinacije je prazan!";
            this.showErrorModal();
        }
        if(this.novaProizvodjac === ""){
            this.errorMessage = "Proizvodjac nove vakcinacije je prazan!";
            this.showErrorModal();
        }
        if(this.novaNezeljena === ""){
            this.novaNezeljena = "nema";
        }
        if(this.primljeneVakcine.length < 2){
            this.primljeneVakcine = [...this.primljeneVakcine, {
                naziv: this.novaNaziv, ekstremitet: this.novaEkstremitet, serijaVakcine: this.novaSerija, proizvodjac: this.novaProizvodjac, nezeljenaReakcija: this.novaNezeljena
            }]
        }
        else{
            this.errorMessage = "Maksimalno vakcinacija(2) je vec evidentirano!";
            this.showErrorModal();
        }
    },
    napraviPotvrdu(){
        let xmlString = `<?xml version="1.0" encoding="UTF-8"?>
        <Obrazac about="string" href="string" rel="string" xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
            <Podaci_o_pacijentu xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
            <Drzavljanstvo xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                <JMBG xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${this.drzavljanstvo}
                </JMBG>
            </Drzavljanstvo>
            <Prezime property="pred:prezime" datatype="xs:string" xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                ${this.prezime}
            </Prezime>
            <Ime property="pred:ime" datatype="xs:string" xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                ${this.ime}
            </Ime>            
            <Pol xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                ${this.pol}
            </Pol>
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
             <Evidencija_o_vakcinaciji xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                <Zdravstvena_ustanova xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${this.zdravstvenaUstanova}
                </Zdravstvena_ustanova>
                <Vakcinacijski_punkt xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${this.vakcinacijskiPunkt}
                </Vakcinacijski_punkt>
                <Podaci_o_lekaru xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    <Ime xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                        ${this.lekarIme}
                    </Ime>
                    <Prezime xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                        ${this.lekarPrezime}
                    </Prezime>
                    <Faksimil xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                        ${this.lekarFaksimil}
                    </Faksimil>
                    <Broj_telefona xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                        ${this.lekarBrojTelefona}
                    </Broj_telefona>
                </Podaci_o_lekaru>
                <Podaci_o_izvrsenim_imunizacijama xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">`;
        this.primljeneVakcine.forEach(element => {
            xmlString += `<Primljena_vakcina xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                <Naziv property="pred:naziv_vakcine" datatype="xs:string" xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${element.naziv}
                </Naziv>
                <Datum_izdavanja>                    
                </Datum_izdavanja>
                <Ekstremitet xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${element.ekstremitet}
                </Ekstremitet>
                <Serija_vakcine xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${element.serijaVakcine}
                </Serija_vakcine>
                <Proizvodjac xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${element.proizvodjac}
                </Proizvodjac>
                <Nezeljena_reakcija xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                    ${element.nezeljenaReakcija}
                </Nezeljena_reakcija>
            </Primljena_vakcina>`;
        });            
        xmlString += `<Odluka_komisije xmlns="http://tim.robot/obrazac_saglasnosti_za_imunizaciju">
                        nema
                    </Odluka_komisije>
                </Podaci_o_izvrsenim_imunizacijama>
            </Evidencija_o_vakcinaciji>
        </Obrazac>`;
        console.log(xmlString);
        this.axios
        .post(`/api/saglasnost/imunizuj`, xmlString, {
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
  },
  mounted(){
        const xml = require("txml");

        this.axios
        .get(`/api/korisnici/me`, {
            headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
            },
        })
        .then((response) => {
            const json = xml.parse(response.data);
            let me = json;
            //console.log(me);
            this.lekarIme = me[1].children[3].children[0];
            this.lekarPrezime = me[1].children[4].children[0];     
        })
        .catch((error) => {
            this.errorMessage = error.response.data;
            this.showErrorModal();
        });
    },
};
</script>
<style>
</style>