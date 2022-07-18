<template>
    <div>
        <h1>Pretraga</h1>
        <br>

        <b-form inline style="margin-left:25px;">
            <label></label>
            <label for="dd"> Osnovna pretraga: </label>
            <div>
            <b-form-input
                type="text"
                v-model="basicSearchInput"
                style="margin-left: 10px;"
                placeholder="Ključna reč"
            />
            </div>

            <b-button @click="onBasicSearch" class="mb-2 mr-sm-2 mb-sm-0" style="margin-left: 10px;">Pretraži sve dokumente</b-button>
        </b-form>

        <br>

        <b-form inline style="margin-left:25px;">
            <label></label>
            <label for="dd"> Napredna pretraga saglasnosti: </label>
            <div>
            <b-form-input
                type="text"
                v-model="imeSaglasnostInput"
                style="margin-left: 10px;"
                placeholder="Ime"
            />
            </div>

            <div>
            <b-form-input
                type="text"
                v-model="prezimeSaglasnostInput"
                style="margin-left: 10px;"
                placeholder="Prezime"
            />
            </div>

            <label style="margin-left:10px;">Naziv vakcine:</label>
            <b-form-select placeholder="Naziv vakcine"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="nazivVakcineSaglasnostInput"
                        :options="nazivVakcineOptions"
                        style="margin-left:10px;">
            </b-form-select>

            <b-form-input
                type="text"
                v-model="datumIzdavanjaSaglasnostInput"
                style="margin-left: 10px;"
                placeholder="Datum izdavanja (yyyy-mm-dd)"
            />

            <label style="margin-left:10px;">Obrazac interesovanja:</label>
            <b-form-select placeholder="Real Estate Name"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="hrefInteresovanjeSaglasnostInput"
                        :options="hrefInteresovanjeOptions"
                        style="margin-left:10px;">
            </b-form-select>

            <label style="margin-left:10px;">Logički operator:</label>
            <b-form-select placeholder="Real Estate Name"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="operatorSaglasnostInput"
                        :options="operatorOptions"
                        style="margin-left:10px;">
            </b-form-select>

            <b-button @click="onSaglasnostSearch" class="mb-2 mr-sm-2 mb-sm-0" style="margin-left: 10px;">Pretraži saglasnosti</b-button>
        </b-form>

        <br>

        <h2>Saglasnosti</h2>
        <br>
        <b-table striped hover :items="obrazacResults" :fields="obrazacResultsFields">
            <template #cell(prikaziDokument)="row">
                <button class="btn btn-primary" @click="onShow(row, 'saglasnost')">Prikaži</button>
            </template>
            <template #cell(prikaziPovezaniDokument)="row">
                <button class="btn btn-primary" @click="onShowLinked(row, 'interesovanje')">Prikaži interesovanje</button>
            </template>
            <template #cell(skiniXHTML)="row">
                <button class="btn btn-primary" @click="onXHTML(row, 'saglasnost')">XHTML</button>
            </template>
            <template #cell(skiniPDF)="row">
                <button class="btn btn-primary" @click="onPDF(row, 'saglasnost')">PDF</button>
            </template>
            <template #cell(skiniRDF)="row">
                <button class="btn btn-primary" @click="onRDF(row, 'saglasnost')">RDF</button>
            </template>
            <template #cell(skiniJSON)="row">
                <button class="btn btn-primary" @click="onJSON(row, 'saglasnost')">JSON</button>
            </template>
        </b-table>

        <br>

        <h2>Potvrde</h2>
        <br>
        <b-table striped hover :items="potvrdaResults" :fields="potvrdaResultsFields">
            <template #cell(prikaziDokument)="row">
                <button class="btn btn-primary" @click="onShow(row, 'potvrda')">Prikaži</button>
            </template>
            <template #cell(skiniXHTML)="row">
                <button class="btn btn-primary" @click="onXHTML(row, 'potvrda')">XHTML</button>
            </template>
            <template #cell(skiniPDF)="row">
                <button class="btn btn-primary" @click="onPDF(row, 'potvrda')">PDF</button>
            </template>
            <template #cell(skiniRDF)="row">
                <button class="btn btn-primary" @click="onRDF(row, 'potvrda')">RDF</button>
            </template>
            <template #cell(skiniJSON)="row">
                <button class="btn btn-primary" @click="onJSON(row, 'potvrda')">JSON</button>
            </template>
        </b-table>

        <br>

        <h2>Sertifikati</h2>
        <br>
        <b-table striped hover :items="sertifikatResults" :fields="sertifikatResultsFields">
            <template #cell(prikaziDokument)="row">
                <button class="btn btn-primary" @click="onShow(row, 'sertifikat')">Prikaži</button>
            </template>
            <template #cell(prikaziPovezaniDokument)="row">
                <button class="btn btn-primary" @click="onShowLinked(row, 'zahtev-za-sertifikat')">Prikaži zahtev</button>
            </template>
            <template #cell(skiniXHTML)="row">
                <button class="btn btn-primary" @click="onXHTML(row, 'sertifikat')">XHTML</button>
            </template>
            <template #cell(skiniPDF)="row">
                <button class="btn btn-primary" @click="onPDF(row, 'sertifikat')">PDF</button>
            </template>
            <template #cell(skiniRDF)="row">
                <button class="btn btn-primary" @click="onRDF(row, 'sertifikat')">RDF</button>
            </template>
            <template #cell(skiniJSON)="row">
                <button class="btn btn-primary" @click="onJSON(row, 'sertifikat')">JSON</button>
            </template>
        </b-table>

        <b-modal ref="xhtml-modal" hide-footer title="Izveštaj" size="xl">
            <div class="d-block text-center" v-html="XHTML">
            </div>
            <b-button class="mt-3" variant="outline-primary" block @click="hideXHTMLModal">Close</b-button>
        </b-modal>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                obrazacResults: [],
                obrazacResultsFields: [
                    {
                        key: '$.about',
                        headerTitle: 'Identifikator',
                        label: 'Identifikator',
                        sortable: true
                    },
                    {
                        key: '$.href',
                        headerTitle: 'Identifikator obrasca interesovanja',
                        label: 'Identifikator obrasca interesovanja'
                    },
                    {
                        key: 'prikaziDokument',
                        headerTitle: 'Prikaz',
                        label: 'Prikaz'
                    },
                    {
                        key: 'prikaziPovezaniDokument',
                        headerTitle: 'Prikaz obrasca interesovanja',
                        label: 'Prikaz obrasca interesovanja'
                    },
                    {
                        key: 'skiniXHTML',
                        headerTitle: 'XHTML',
                        label: 'XHTML'
                    },
                    {
                        key: 'skiniPDF',
                        headerTitle: 'PDF',
                        label: 'PDF'
                    },
                    {
                        key: 'skiniRDF',
                        headerTitle: 'RDF',
                        label: 'RDF'
                    },
                    {
                        key: 'skiniJSON',
                        headerTitle: 'JSON',
                        label: 'JSON'
                    },
                ],
                potvrdaResults: [],
                potvrdaResultsFields: [
                    {
                        key: '$.about',
                        headerTitle: 'Identifikator',
                        label: 'Identifikator',
                        sortable: true
                    },
                    {
                        key: 'prikaziDokument',
                        headerTitle: 'Prikaz',
                        label: 'Prikaz'
                    },
                    {
                        key: 'skiniXHTML',
                        headerTitle: 'XHTML',
                        label: 'XHTML'
                    },
                    {
                        key: 'skiniPDF',
                        headerTitle: 'PDF',
                        label: 'PDF'
                    },
                    {
                        key: 'skiniRDF',
                        headerTitle: 'RDF',
                        label: 'RDF'
                    },
                    {
                        key: 'skiniJSON',
                        headerTitle: 'JSON',
                        label: 'JSON'
                    },
                ],
                sertifikatResults: [],
                sertifikatResultsFields: [
                    {
                        key: '$.about',
                        headerTitle: 'Identifikator',
                        label: 'Identifikator',
                        sortable: true
                    },
                    {
                        key: '$.href',
                        headerTitle: 'Identifikator zahteva',
                        label: 'Identifikator zahteva'
                    },
                    {
                        key: 'prikaziDokument',
                        headerTitle: 'Prikaz',
                        label: 'Prikaz'
                    },
                    {
                        key: 'prikaziPovezaniDokument',
                        headerTitle: 'Prikaz zahteva',
                        label: 'Prikaz zahteva'
                    },
                    {
                        key: 'skiniXHTML',
                        headerTitle: 'XHTML',
                        label: 'XHTML'
                    },
                    {
                        key: 'skiniPDF',
                        headerTitle: 'PDF',
                        label: 'PDF'
                    },
                    {
                        key: 'skiniRDF',
                        headerTitle: 'RDF',
                        label: 'RDF'
                    },
                    {
                        key: 'skiniJSON',
                        headerTitle: 'JSON',
                        label: 'JSON'
                    },
                ],
                basicSearchInput: '',
                minDate: new Date(),
                dateFrom: new Date(),
                dateTo: new Date(new Date().setDate(new Date().getDate() + 1)),
                XHTML: '',
                imeSaglasnostInput: '',
                prezimeSaglasnostInput: '',
                nazivVakcineSaglasnostInput: '',
                nazivVakcineOptions: ['', "Pfizer", "Sputnik", "Sinopharm", "AZ", "Moderna"],
                datumIzdavanjaSaglasnostInput: '',
                hrefInteresovanjeSaglasnostInput: '',
                hrefInteresovanjeOptions: [],
                operatorSaglasnostInput: 'AND',
                operatorOptions: ['AND', 'OR'],
            }
        },
        methods: {

            onBasicSearch() {
                // let body = {
                //     start: new Date(this.dateFrom).toISOString().split('T')[0],
                //     end: new Date(this.dateTo).toISOString().split('T')[0]
                // };

                this.axios.get(`/api/search/regular?query=${this.basicSearchInput}`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    let parseString = require('xml2js').parseString;
                    let stripNS = require('xml2js').processors.stripPrefix;
                    parseString(response.data, { tagNameProcessors: [stripNS] }, (err, result) => {
                        this.obrazacResults = result.entityList.Obrazac;
                        this.potvrdaResults = result.entityList.Potvrda;
                        this.sertifikatResults = result.entityList.Sertifikat;
                        this.fixIdentifiersInAboutAndHref(this.obrazacResults);
                        this.fixIdentifiersInAboutAndHref(this.potvrdaResults);
                        this.fixIdentifiersInAboutAndHref(this.sertifikatResults);
                    });
                    // console.log(this.searchResults);
                })
                .catch(error => {
                    console.log(error);
                }); 
            },

            fixIdentifiersInAboutAndHref(results) {
                results.forEach(obj => {
                    obj.$.about = obj.$.about.split("/").pop();
                    if (obj.$.href) {
                        obj.$.href = obj.$.href.split("/").pop();
                    }
                });
            },

            onShow(row, type) {
                this.axios.get(`/api/${type}/${row.item.$.about}/show`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    this.XHTML = response.data;
                    this.showXHTMLModal();
                })
                .catch(error => {
                    console.log(error);
                }); 
            },

            onShowLinked(row, type) {
                this.axios.get(`/api/${type}/${row.item.$.href}/show`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    this.XHTML = response.data;
                    this.showXHTMLModal();
                })
                .catch(error => {
                    console.log(error);
                }); 
            },

            onXHTML(row, type) {
                window.open(`/api/${type}/${row.item.$.about}/xhtml`);
            },

            onPDF(row, type) {
                window.open(`/api/${type}/${row.item.$.about}/pdf`);
            },

            onRDF(row, type) {
                window.open(`/api/${type}/metadata-rdf/${row.item.$.about}`);
            },

            onJSON(row, type) {
                window.open(`/api/${type}/metadata-json/${row.item.$.about}`);
            },

            hideXHTMLModal() {
                this.$refs['xhtml-modal'].hide()
            },

            showXHTMLModal() {
                this.$refs['xhtml-modal'].show()
            },

            populateSearchOptions() {
                this.hrefInteresovanjeOptions = [''];

                this.axios.get(`/api/interesovanje`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    let parseString = require('xml2js').parseString;
                    let stripNS = require('xml2js').processors.stripPrefix;
                    parseString(response.data, { tagNameProcessors: [stripNS] }, (err, result) => {
                        result.entityList.Obrazac_interesovanja.forEach(obj => {
                            this.hrefInteresovanjeOptions.push("http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju/" + obj.Licni_podaci[0].JMBG[0]);
                        });

                        this.hrefInteresovanjeOptions = this.hrefInteresovanjeOptions.filter(this.onlyUnique);
                    });
                })
                .catch(error => {
                    console.log(error);
                }); 
            },

            onlyUnique(value, index, self) {
                return self.indexOf(value) === index;
            },

            onSaglasnostSearch() {
                this.obrazacResults = [];

                let hrefQuery = this.hrefInteresovanjeSaglasnostInput;
                if (this.hrefInteresovanjeSaglasnostInput) {
                    hrefQuery = `<${this.hrefInteresovanjeSaglasnostInput}>`
                }

                let queryParams = `ime=${this.imeSaglasnostInput}&prezime=${this.prezimeSaglasnostInput}&nazivVakcine=${this.nazivVakcineSaglasnostInput}&` +
                                    `datumIzdavanja=${this.datumIzdavanjaSaglasnostInput}&hrefInteresovanje=${hrefQuery}` +
                                    `&logicalAnd=${this.operatorSaglasnostInput === 'AND' ? 'true' : 'false'}`;

                this.axios.get(`/api/search/saglasnost/advanced?${queryParams}`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem('token'),
                        },
                    })
                .then((response) => {
                    let parseString = require('xml2js').parseString;
                    let stripNS = require('xml2js').processors.stripPrefix;
                    parseString(response.data, { tagNameProcessors: [stripNS] }, (err, result) => {
                        this.obrazacResults = result.entityList.Obrazac;
                        this.fixIdentifiersInAboutAndHref(this.obrazacResults);
                    });
                })
                .catch(error => {
                    console.log(error);
                }); 
            }

        },

        mounted() {
            this.populateSearchOptions();
        }
    }
</script>
