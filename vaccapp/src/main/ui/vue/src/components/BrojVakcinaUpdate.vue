<template>
    <div>
        <h1>Broj vakcina</h1>
        <br>

        <div v-for="brojVakcina in brojVakcinaArr" :key="brojVakcina.Vakcina[0]">
            <b-form inline style="margin-left:25px;">
                <label></label>
                <label for="dd"> Vakcina: {{ brojVakcina.Vakcina[0] }}</label>
                <label></label>
                <label for="dd" style="margin-left: 100px;"> Broj dostupnih vakcina: </label>
                <b-form-input
                    type="text"
                    v-model="brojVakcina.Broj[0]"
                    style="margin-left: 10px;"
                />

                <b-button @click="onSubmit(brojVakcina)" class="mb-2 mr-sm-2 mb-sm-0" style="margin-left: 10px;">Ažuriraj</b-button>
            </b-form>
            <br>
        </div>

        <br>

        <b-modal ref="success-modal" hide-footer title="Success">
            <div class="d-block text-center">
                <p>Uspešno ste ažurirali broj vakcine {{ trenutnaVakcina }}.</p>
            </div>
            <b-button class="mt-3" variant="outline-success" block @click="hideSuccessModal">Close</b-button>
        </b-modal>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                brojVakcinaArr: [],
                trenutnaVakcina: ''
            }
        },
        methods: {

            onSubmit(brojVakcina) {
                let vakcina = brojVakcina.Vakcina[0];
                let broj = brojVakcina.Broj[0];

                this.axios.get(`/api/broj-vakcina/update?vakcina=${vakcina}&broj=${broj}`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    this.trenutnaVakcina = vakcina;
                    this.showSuccessModal();
                    this.loadBrojVakcina();
                })
                .catch(error => {
                    console.log(error);
                }); 
            },

            loadBrojVakcina() {
                this.axios.get(`/api/broj-vakcina`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    let parseString = require('xml2js').parseString;
                    let stripNS = require('xml2js').processors.stripPrefix;
                    parseString(response.data, { tagNameProcessors: [stripNS] }, (err, result) => {
                        this.brojVakcinaArr = result.entityList.Broj_vakcina;
                    });
                })
                .catch(error => {
                    console.log(error);
                }); 
            },

            hideSuccessModal() {
                this.$refs['success-modal'].hide()
            },

            showSuccessModal() {
                this.$refs['success-modal'].show()
            },

        },

        mounted() {
            this.loadBrojVakcina();
        }
    }
</script>
