<template>
<div>
    <b-row style="margin-left: 20px;">
        <p style="text-align: left; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Odaberite JMBG pacijenta:</p>
        <b-form-select
            id="jmbg"
            name="jmbg"
            class="mb-2 mr-sm-2 mb-sm-0"
            v-model="jmbg"
            :options="jmbgs"
            style="width:33%"
        >
        </b-form-select>
        <b-button style="margin-bottom: 20px;" variant="primary" @click="pretraziDokumenta">
            Pretrazi dokumenta
        </b-button>
    </b-row>
    <b-row>
        <b-col>
            <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Saglasnost:</p>
            <iframe
                id="saglasnost"
                style="border: 1px solid #666ccc"
                frameborder="1"
                scrolling="auto"
                height="840"
                width="600"
                :src="saglasnost"               
            ></iframe>
        </b-col>
        <b-col>
            <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Potvrda:</p>
            <iframe
                id="potvrda"
                style="border: 1px solid #666ccc"
                frameborder="1"
                scrolling="auto"
                height="840"
                width="600"
                :src="potvrda"               
            ></iframe>
        </b-col>
        <b-col>
            <p style="text-align: center; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Zahtev za sertifikat:</p>
            <iframe
                id="zahtev"
                style="border: 1px solid #666ccc"
                frameborder="1"
                scrolling="auto"
                height="840"
                width="600"
                :src="zahtev"               
            ></iframe>
        </b-col>
    </b-row>
    <b-row style="margin-top:20px;">
        <b-col style="margin-left:50px;">          
        </b-col>
        <b-col style="margin-left:50px;">
            <b-row>
            <p style="text-align: left; font-family: sans-serif; font-weight: bold; font-size: large; padding: 5px;">Razlog za odbijanje:</p>
            <b-form-input
                id="razlog"
                name="razlog"
                placeholder="Razlog"
                class="mb-2 mr-sm-2 mb-sm-0"
                style="width: 25%;"
                v-model="razlog"
            >
            </b-form-input>
            <b-button style="margin-bottom: 30px; padding: 5px; font-size: large; font-weight: bold;" variant="danger" @click="odbijZahtev">
                Odbij zahtev
            </b-button>
            </b-row>
            <b-row>
            <b-button style="margin-bottom: 20px; padding: 10px; font-size: large; font-weight: bold;" variant="success" @click="prihvatiZahtev">
                Prihvati zahtev
            </b-button>
            </b-row>
        </b-col>
    </b-row>
</div>
</template>
<script>
export default{
    name: "SluzbenikHome",
    data() {
        return {
            saglasnost: "",
            potvrda: "",
            zahtev: "",
            jmbg: "",
            potvrde: [],
            zahtevi: [],
            jmbgs: [],

            errorMessage: "",

            razlog: "",
        };
    },
    methods:{
        pretraziDokumenta(){
            this.saglasnost = `/documents/saglasnost_${this.jmbg}.pdf`;
            this.potvrde.forEach(element => {
                if(element.jmbg === this.jmbg)
                    this.potvrda = `/documents/potvrda-o-vakcinaciji_${element.id}.pdf`
            });
            //this.zahtev = `/documents/zahtev-zeleni_${this.zahtevi[0]}.pdf`
            for(let i=0; i<this.jmbgs.length; i+=1){
                if(this.jmbg === this.jmbgs[i])
                    this.zahtev = `/documents/zahtev-zeleni_${this.zahtevi[i]}.pdf`
            }
        },
        odbijZahtev(){
            this.obradiZahtev("odbij");
        },
        prihvatiZahtev(){
            this.obradiZahtev("prihvati");
        },
        obradiZahtev(e){
            let z = this.zahtev.replace("/documents/zahtev-zeleni_", "").replace(".pdf", "");
            let path = `/api/zahtev-za-sertifikat/${e}/${z}` +
                (e === "odbij" ? `?razlog=${this.razlog}` : "");
            this.axios
                .get(path, {
                    headers: {
                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                    },
                })
                .then((response) => {
                    console.log("success");
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
                .get(`/api/zahtev-za-sertifikat/neobradjeni`, {
                    headers: {
                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                    },
                })
                .then((response) => {
                    const json = xml.parse(response.data);
                    this.zahtevi = json[1].children.map((el) => el.children[0].children[0]);
                    this.zahtev = `/documents/zahtev-zeleni_${this.zahtevi[0]}.pdf`
                    this.jmbgs = json[1].children.map((el) => el.children[1].children[4].children[0]);
                    this.jmbg = this.jmbgs[0];
                    //dodaj u zahteve
                })
                .catch((error) => {
                    this.errorMessage = error.response.data;
                    this.showErrorModal();
                });
            this.axios
                .get(`/api/potvrda`, {
                    headers: {
                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                    },
                })
                .then((response) => {
                    const json = xml.parse(response.data);
                    this.potvrde = []
                    for(let i=0; i<json[1].children.length; i+=1){
                        //console.log(json[1].children[i]);
                        this.potvrde.push({
                            id: json[1].children[i].children[0].children[0],
                            jmbg: json[1].children[i].children[2].children[3].children[0]
                        })
                    }

                    console.log(this.potvrde);
                })
                .catch((error) => {
                    this.errorMessage = error.response.data;
                    this.showErrorModal();
                });
        }
};
</script>
<style>
</style>