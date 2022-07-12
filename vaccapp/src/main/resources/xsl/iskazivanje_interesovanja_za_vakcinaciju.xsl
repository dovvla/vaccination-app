<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju"
                 version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Interesovanje</title>
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
                    .indent-vaccine {
                    margin: 2px 0px 0px 20vw;
                    margin-left: 20vw;
                    font-size: 1.3em;
                    padding: 0px;
                    }
                    .indent-potpis {
                    border-top: 1px solid black;
                    width: 25%;
                    margin: 10px 0px 10vh 60%;
                    padding: 15px;
                    text-align: center;
                    }
                </style>
            </head>
            <body>
                <h1 style="margin-top: 10vh;">Iskazivanje interesovanja za vakcinisanje protiv COVID-19</h1>
                <p class="indent-paragraph">Drzavljanstvo:
                    <b>
                        <xsl:value-of select="//i:Drzavljanstvo/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">JMBG:
                    <b>
                        <xsl:value-of select="//i:JMBG/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Ime:
                    <b>
                        <xsl:value-of select="//i:Ime/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Prezime:
                    <b>
                        <xsl:value-of select="//i:Prezime/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Adresa elektronske poste:
                    <b>
                        <xsl:value-of select="//i:Imejl/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Broj mobilnog telefona (navesti broj u formatu 06X... bez razmaka i crtica):
                    <b>
                        <xsl:value-of select="//i:Broj_mobilnog_telefona/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Broj fiksnog telefona (navesti broj u formatu (DDD) DDD-DDD):
                    <b>
                        <xsl:value-of select="//i:Broj_fiksnog_telefona/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Lokacija:
                    <b>
                        <xsl:value-of select="//i:Zeljena_lokacija_vakcinacije/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph" style="margin-right: 10vw;">
                    Iskazujem interesovanje da primim iskljucivo vakcinu sledecih proizvodjaca za koji Agencija
                    za lekove i medicinska sredstva potvrdi bezbednost, efikasnost i kvalitet i izda dozvolu za upotrebu
                    leka:
                </p>
                <p class="indent-vaccine">
                    <b>
                        <xsl:value-of select="//i:Zeljena_vakcina/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">Da li ste dobrovoljni davalac krvi:</p>
                <p class="indent-vaccine">
                    <b>
                        <xsl:value-of select="//i:Davalac_krvi/text()"/>
                    </b>
                </p>
                <p class="indent-paragraph">
                    <xsl:value-of select="//i:Datum/text()"/>
                </p>
                <p class="indent-potpis">Potpis</p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>