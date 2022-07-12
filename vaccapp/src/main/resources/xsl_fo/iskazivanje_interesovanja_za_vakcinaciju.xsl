<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="interesovanje"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="2.5cm"
                        margin-bottom="2cm"
                        margin-left="2cm"
                        margin-right="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="interesovanje">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="15px" font-weight="bold" padding="20px"
                              text-align="center">
                        Iskazivanje interesovanja za vakcinisanje protiv COVID-19
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Drzavljanstvo:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Drzavljanstvo/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        JMBG:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:JMBG/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Ime:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Ime/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Prezime:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Prezime/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Adresa elektronske poste:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Imejl/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Broj mobilnog telefona (navesti broj u formatu 06X... bez razmaka i crtica):
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Broj_mobilnog_telefona/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Broj fiksnog telefona (navesti broj u formatu (DDD) DDD-DDD):
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Broj_fiksnog_telefona/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        Lokacija:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Zeljena_lokacija_vakcinacije/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="13px" padding="20px">
                        Iskazujem interesovanje da primim iskljucivo vakcinu sledecih proizvodjaca za koji Agencija
                        za lekove i medicinska sredstva potvrdi bezbednost, efikasnost i kvalitet i izda dozvolu za
                        upotrebu leka:
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Zeljena_vakcina/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="13px" padding="20px">
                        Da li ste dobrovoljni davalac krvi:
                    </fo:block>
                    <fo:block font-size="13px" padding="10px">
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Davalac_krvi/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="13px" padding="20px">
                        <fo:inline>
                            <xsl:value-of select="//i:Datum/text()"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block-container>
                        <fo:block-container width="40%" left="60%" top="0in" position="absolute">
                            <fo:block font-family="sans-serif" font-size="12px" text-align="center"
                                      linefeed-treatment="preserve" margin="0" border-top="1px solid black">
                                Potpis
                            </fo:block>
                        </fo:block-container>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>