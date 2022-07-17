<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://tim.robot/izvestaj_o_imunizaciji"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="izvestaj"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1.3cm"
                        margin-bottom="2cm"
                        margin-left="1.3cm"
                        margin-right="1.3cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="izvestaj">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block-container width="40%" left="30%" top="0in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="14px" font-weight="bold" text-align="center">
                            Izvestaj o imunizaciji
                        </fo:block>
                    </fo:block-container>
                    <fo:block-container top="1in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="12px" text-align="left">
                            <fo:inline>Izvestaj se odnosi na period od</fo:inline>
                            <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //i:Datum_od)"/>
                            </fo:inline>
                            <fo:inline> do</fo:inline>
                            <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //i:Datum_do)"/>.
                            </fo:inline>
                        </fo:block>        
                    </fo:block-container>
                    <fo:block-container top="2in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="12px" text-align="left">
                            U napomenutom vremenskom intervalu je:
                        </fo:block>
                        <fo:list-block padding="4pt" font-family="sans-serif" font-size="12px" text-align="left">
                            <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>•</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline>podneto</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Podnetih_interesovanja_imunizacije)"/>
                                    </fo:inline>
                                    <fo:inline> dokumenata o interesovanju za imunizaciju;</fo:inline>                                  
                                </fo:block>
                            </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>•</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline>primljeno</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Zahtevi_za_zeleni_sertifikat)"/>
                                    </fo:inline>
                                    <fo:inline> zahteva za digitalni zeleni sertifikat, od kojih je</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Izdatih_zelenih_sertifikata)"/>
                                    </fo:inline>
                                    <fo:inline> izdato.</fo:inline>
                                </fo:block>
                            </fo:list-item-body>
                            </fo:list-item>
                        </fo:list-block>
                    </fo:block-container>   
                    <fo:block-container top="3in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="12px" text-align="left">
                            <fo:inline>Dato je</fo:inline>
                            <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //i:Ukupno_doza)"/>
                            </fo:inline>
                            <fo:inline> doza vakcine protiv COVID-19 virusa u sledecoj kolicini:</fo:inline>
                        </fo:block>
                        <fo:table font-family="sans-serif" font-size="12px" text-align="center" border-width="1px" border-style="solid">
                            <fo:table-column column-width="75mm"/>
                            <fo:table-column column-width="75mm"/>

                            <fo:table-header>
                            <fo:table-row border-width="1px" border-style="solid">
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block font-weight="bold">Redni broj doze</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block font-weight="bold">Broj datih doza</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            </fo:table-header>

                            <fo:table-body>
                            <fo:table-row border-width="1px" border-style="solid">
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block font-weight="bold">1</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block><xsl:value-of select="concat(' ', //i:Broj_date_prve_doze)"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border-width="1px" border-style="solid">
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block font-weight="bold">2</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block><xsl:value-of select="concat(' ', //i:Broj_date_druge_doze)"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border-width="1px" border-style="solid">
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block font-weight="bold">3</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-width="1px" border-style="solid">
                                <fo:block><xsl:value-of select="concat(' ', //i:Broj_date_trece_doze)"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            </fo:table-body>

                        </fo:table>
                    </fo:block-container>
                    <fo:block-container top="5in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="12px" text-align="left">
                            Raspodela po proizvodjacima je:
                        </fo:block>
                        <fo:list-block padding="4pt" font-family="sans-serif" font-size="12px" text-align="left">
                            <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>•</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline font-weight="bold">Pfizer, BioNTech</fo:inline>
                                    <fo:inline> -</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Broj_pfizer_vakcina)"/>
                                    </fo:inline>
                                    <fo:inline> doza;</fo:inline>                                  
                                </fo:block>
                            </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>•</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline font-weight="bold">Sinopharm</fo:inline>
                                    <fo:inline> -</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Broj_sinopharm_vakcina)"/>
                                    </fo:inline>
                                    <fo:inline> doza;</fo:inline>                                  
                                </fo:block>
                            </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>•</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline font-weight="bold">Sputnik V</fo:inline>
                                    <fo:inline> -</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Broj_sputnik_vakcina)"/>
                                    </fo:inline>
                                    <fo:inline> doza;</fo:inline>                                  
                                </fo:block>
                            </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>•</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline font-weight="bold">AstraZeneca, Oxford</fo:inline>
                                    <fo:inline> -</fo:inline>
                                    <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //i:Broj_az_vakcina)"/>
                                    </fo:inline>
                                    <fo:inline> doza.</fo:inline>                                  
                                </fo:block>
                            </fo:list-item-body>
                            </fo:list-item>
                        </fo:list-block>
                    </fo:block-container>
                    <fo:block-container top="7in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="12px" text-align="left">
                            <fo:inline>Datum izdavanja:</fo:inline>
                            <fo:inline text-decoration="underline">
                            <xsl:value-of select="concat(' ', //i:Datum)"/>
                            </fo:inline>
                            <fo:inline> godine</fo:inline>  
                        </fo:block>
                    </fo:block-container>
                    <fo:block-container top="7in" position="absolute">
                        <fo:block font-family="sans-serif" font-size="10px" text-align="center" left="3in" text-decoration="overline">
                            Potpis
                        </fo:block>
                    </fo:block-container>      
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>