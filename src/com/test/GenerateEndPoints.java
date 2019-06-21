package com.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateEndPoints {


    public static void main (String[] args) {
        //RESTEndPointsRetailProduct.csv
        // The name of the file to open.
        String fileName = "/home/ruth/Documents/Mojix/RESTEndPointsRetailProduct.csv";
        String fileDestiny = "/home/ruth/Documents/Mojix/RESTEndPointsRetailProduct_RESULT.csv";
        List<String> results = getData(fileName);
        writeFile(fileDestiny, results);


    }

    public static List<String> getData(String fileName){
        List<String> results = new ArrayList<>();
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int position = 0;

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] vector = line.split(",");
                position++;
                String tag = (vector.length>0 && vector[0]!=null)?vector[0]:"";
                String path = (vector.length>1 && vector[1]!=null)?vector[1]:"";
                String method = (vector.length>3 && vector[3]!=null)?vector[3]:"";
                String notes = (vector.length>4 && vector[4]!=null)?vector[4]:"";
                // 0: Tags
                // 1: Item Chain Platform API paths
                // 2: ViZix API paths
                // 3: Method
                // 4: Notes / Swagger Description
                StringBuffer data = new StringBuffer("");
                data.append(
                        "    @"+method+"\n" +
                        "    @Path(\""+path+"\")\n" +
                        "    @Produces(MediaType.APPLICATION_JSON)\n" +
                        "    @RequiresAuthentication\n" +
                        "    @ApiOperation(\n" +
                        "            tags = {\""+tag+"\"},\n" +
                        "            value = \""+notes+"\",\n" +
                        "            position = "+position+"\n" +
                        "    )\n" +
                        "    public Response getItemChainPlatform"+position+"(@QueryParam(\"from\") Integer from, @QueryParam(\"to\") Integer to) {\n" +
                        "        Map<String, Object> mapResponse = new HashMap<>();\n" +
                        "        mapResponse.put(\"result\", \"You do not have permissions to see the results.\");\n" +
                        "        return RestUtils.sendOkResponse(mapResponse);\n" +
                        "    }");
                results.add("\n");
                results.add(data.toString());
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return results;
    }

    public static void writeFile(String fileName, List<String> results){

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String data : results) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        System.out.println("file created successfully");
    }
}
