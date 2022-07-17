<template>
    <div>
        <h1>Izveštaj</h1>
        <br>

        <b-form inline style="margin-left:25px;">
            <label></label>
            <label for="dd"> Start time: </label>
            <div>
            <b-form-datepicker
                v-model="dateFrom"
                locale="en"
                class="mb-2"
                style="margin-left: 10px;"
                required
            ></b-form-datepicker>
            </div>
            <label></label>
            <label for="dd" style="margin-left: 10px;"> End: </label>
            <b-form-datepicker
                v-model="dateTo"
                locale="en"
                class="mb-2"
                style="margin-left: 10px;"
                required
            ></b-form-datepicker>

            <b-button @click="onSubmit" class="mb-2 mr-sm-2 mb-sm-0" style="margin-left: 10px;">Pregled izveštaja</b-button>
            <b-button @click="onXHTML" class="mb-2 mr-sm-2 mb-sm-0" style="margin-left: 10px;">XHTML</b-button>
            <b-button @click="onPDF" class="mb-2 mr-sm-2 mb-sm-0" style="margin-left: 10px;">PDF</b-button>
        </b-form>

        <br>

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
                minDate: new Date(),
                dateFrom: new Date(),
                dateTo: new Date(new Date().setDate(new Date().getDate() + 1)),
                XHTML: ''
            }
        },
        methods: {

            onSubmit() {
                let body = {
                    start: new Date(this.dateFrom).toISOString().split('T')[0],
                    end: new Date(this.dateTo).toISOString().split('T')[0]
                };

                this.axios.get(`/api/izvestaj/${body.start}/${body.end}`, {
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

            onXHTML() {
                let body = {
                    start: new Date(this.dateFrom).toISOString().split('T')[0],
                    end: new Date(this.dateTo).toISOString().split('T')[0]
                };

                window.open(`/api/izvestaj/${body.start}/${body.end}/xhtml`);
            },

            onPDF() {
                let body = {
                    start: new Date(this.dateFrom).toISOString().split('T')[0],
                    end: new Date(this.dateTo).toISOString().split('T')[0]
                };

                window.open(`/api/izvestaj/${body.start}/${body.end}/pdf`);
            },

            hideXHTMLModal() {
                this.$refs['xhtml-modal'].hide()
            },

            showXHTMLModal() {
                this.$refs['xhtml-modal'].show()
            },

        },

        mounted() {

        }
    }
</script>
