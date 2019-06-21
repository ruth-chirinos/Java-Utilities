package com.migration.red;

import java.io.*;

public class GenerateEPCIS {

    public static int quantity_events = 500;
    public static int quantity_file = 20;

    public static void main(String args[]){
        StringBuilder arrayEpcis = new StringBuilder("");
        arrayEpcis.append("{\n" +
            "  \"events\": [\n");
        for (int i = 19001; i <= 20000; i++) {
            arrayEpcis.append(getEpcis(i));
            arrayEpcis.append(",");
        }
        arrayEpcis.append(
                "  ]\n" +
                "}");

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/home/ruth/Documents/toDelete/EPCIS/FileEPCIS"+quantity_file+".js"), "utf-8"));
            writer.write(arrayEpcis.toString());
        } catch (IOException ex) {
            // Report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }



    public static String getEpcis(int identifier){
        StringBuilder epcis = new StringBuilder("");
        epcis.append(        "    {\n" +
                "      \"type\": \"ObjectEvent\",\n" +
                "      \"eventTime\": \"2019-06-15T18:00:00.000+0000\",\n" +
                "      \"disposition\": \"urn:epcglobal:cbv:disp:sellable_accessible\",\n" +
                "      \"readPoint\": \"READPOINT-10000000\",\n" +
                "      \"bizStep\": \"urn:epcglobal:cbv:bizstep:cycle_counting\",\n" +
                "      \"bizLocation\": \"urn:epc:id:sgln:9999999.00101.1\",\n" +
                "      \"epcList\": [\n" +
                "        {\n" +
                "          \"epc\": \"urn:epc:id:sgtin:366332.0801667."+identifier+"\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"action\": \"ADD\",\n" +
                "      \"extension\": {\n" +
                "        \"generation\": \"generation_edge_epcis\"\n" +
                "      }\n" +
                "    }\n" );
        return epcis.toString();
    }
}
