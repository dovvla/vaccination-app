<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://tim.robot/izvestaj_o_imunizaciji" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Izvestaj</title>
                <style type="text/css">
                    body {
                    margin: 0;
                    font-family: Arial, sans-serif;
                    overflow-x: hidden;
                    }
                    h1 {
                    text-align: center;
                    padding: 20px;
                    font-weight: bold;
                    }
                    .indent-paragraph {
                    margin-left: 10vw;
                    font-size: 1.5em;
                    }
                    .flex-row-between {
                    display: flex;
                    flex-direction: row;
                    align-items: center;
                    justify-content: space-between;
                    }
                    table, th, td {
                    border: 1px solid black;
                    border-collapse: collapse;
                    }
                    th, td {
                    padding: 5px;
                    text-align: center;
                    }
                    .overline {
                        width: 100px;
                        border-top: 2px solid black;
                    }
                </style>
            </head>
            <body>
                <p style="font-size: 1.5em; text-align: center; margin-top: 50px">
                        <b>
                            Izvestaj o imunizaciji
                        </b>
                </p>
                <p style="text-align: left; margin-top:100px; margin-left:250px;">
                    Izvestaj se odnosi na period od<b><xsl:value-of select="concat(' ', //i:Datum_od)"/></b> do<b><xsl:value-of select="concat(' ', //i:Datum_do)"/>.</b> 
                </p>
                <p style="text-align: left; margin-top:70px; margin-left:250px;">
                    U napomenutom vremenskom intervalu je:
                </p>
                <ul style="margin-left:250px;">
                    <li>
                        podneto<b><xsl:value-of select="concat(' ', //i:Podnetih_interesovanja_imunizacije)"/></b> dokumenata o interesovanju za imunizaciju;
                    </li>
                    <li>
                        primljeno<b><xsl:value-of select="concat(' ', //i:Zahtevi_za_zeleni_sertifikat)"/></b> zahteva za digitalni zeleni sertifikat, od kojih je<b><xsl:value-of select="concat(' ', //i:Izdatih_zelenih_sertifikata)"/></b> izdato.
                    </li>
                </ul>
                <p style="text-align: left; margin-top:100px; margin-left:250px;">
                    Dato je<b><xsl:value-of select="concat(' ', //i:Ukupno_doza)"/></b> doza vakcine protiv COVID-19 virusa u sledecoj kolicini:
                </p>
                <table style="margin-left:250px; width:50%;">
                    <tr>
                        <th style="width:50%">
                            <b>Redni broj doze</b>
                        </th>
                        <th style="width:50%">
                            <b>Broj datih doza</b>
                        </th>
                    </tr>
                    <tr>
                        <td><b>1</b></td>
                        <td><xsl:value-of select="concat(' ', //i:Broj_date_prve_doze)"/></td>
                    </tr>
                    <tr>
                        <td><b>2</b></td>
                        <td><xsl:value-of select="concat(' ', //i:Broj_date_druge_doze)"/></td>
                    </tr>
                    <tr>
                        <td><b>3</b></td>
                        <td><xsl:value-of select="concat(' ', //i:Broj_date_trece_doze)"/></td>
                    </tr>
                </table>
                <p style="text-align: left; margin-top:100px; margin-left:250px;">
                   Raspodela po proizvodjacima je:
                </p>
                <ul style="margin-left:250px;">
                    <li>
                    <b>Pfizer, BioNTech</b> -<b><xsl:value-of select="concat(' ', //i:Broj_pfizer_vakcina)"/></b> doza;
                    </li>
                    <li>
                    <b>Sinopharm</b> -<b><xsl:value-of select="concat(' ', //i:Broj_sinopharm_vakcina)"/></b> doza;
                    </li>
                    <li>
                    <b>Sputnik V</b> -<b><xsl:value-of select="concat(' ', //i:Broj_sputnik_vakcina)"/></b> doza;
                    </li>
                    <li>
                    <b>AstraZeneca, Oxford</b> -<b><xsl:value-of select="concat(' ', //i:Broj_az_vakcina)"/></b> doza.
                    </li>
                </ul>
                <p style="text-align: left; margin-top:150px; margin-left:250px;">
                    Datum izdavanja:<u><xsl:value-of select="concat(' ', //i:Datum)"/></u> godine
                </p>
                <p style="text-align: center; margin-left:60%;" class="overline">
                    Potpis
                </p>
            </body>            
        </html>
    </xsl:template>
</xsl:stylesheet>