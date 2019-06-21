package main.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

    public static void main (String[] args) {
        //String sentencePattern = "\\w*db\\.((\\w+\\.)|(\\w*))" + keyword + "\\(\\{*(.)*\\}*\\)";
        String functionMinified = secondValue();
        //String sentencePattern = "\\w*db\\.((.*\\.)|(.*))shutdown";
        String sentencePattern = "\\w*db\\.((.*\\.)|(.*))shutdown";
        //String sentencePattern = "\\w*db\\.((\\w+\\.)|(\\w*))" + keyword + "\\(\\{*(.)*\\}*\\)";

        // db.loquesea.shut
        Pattern p = Pattern.compile(sentencePattern);
        Matcher m = p.matcher(functionMinified);

        System.out.println("Result: "+ m.find());
    }

    public static String secondValue(){
        return "function(options){table.columnNames=[\"Group\",\"GroupQty\",\"SKU\",\"Desc\",\"QtyOnHand\",\"UPCs\",\"EPCs\"];table.totalRows=finalResult.length;varskip=options.pageNumber>0?options.pageSize(options.pageNumber-1):0;varlimit=(options.pageSize!=-1&&options.pageSize!==0)?options.pageSize:table.totalRows;vardata=finalResult.slice(skip,(limit+skip));table.data=data;returntable;}";
    }

    public static String getString(){
        return "function(options) {\n" +
                "\n" +
                //"db.things.find(queryMatchGiv).forEach(function(x) {" +
                //"    db.loquesea.shutdown//**************************************************************************************\n" +
                // db.getSisterDB("admin4").shutdownServer();
                //"    db.adminCommand(\"shutdown\")\n" +
                "    // Table script initialization\n" +
                "//**************************************************************************************\n" +
                "    db.getSisterDB(\"admin4\").shutdownServer()"+
                "    //**************************************************************************************\n" +
                "\n" +
                "    var table = {};\n" +
                "    table.options = options; //Options from vizix filters\n" +
                "    table.data = [];\n" +
                "    table.title = \"Cycle Count\";\n" +
                "    table.columnNames = [];\n" +
                "\n" +
                "    /***************************** COLUMN NAMES FOR RESULT ************************/\n" +
                "    table.columnNames = [\n" +
                "        \"Group\",\n" +
                "        \"GroupQty\",\n" +
                "        \"SKU\",\n" +
                "        \"Desc\",\n" +
                "        \"QtyOnHand\",\n" +
                "        \"UPCs\",\n" +
                "        \"EPCs\"\n" +
                "    ];\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    // Variables\n" +
                "    //**************************************************************************************\n" +
                "\n" +
                "    var start = new Date();\n" +
                "    start.setHours(0, 0, 0, 0);\n" +
                "\n" +
                "    var end = new Date();\n" +
                "    end.setHours(23, 59, 59, 999);\n" +
                "\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    /*var start = new Date(2017, 6, 17);\n" +
                "    start.setHours(0, 0, 0, 0);\n" +
                "\n" +
                "    var end = new Date(2017, 6, 17);\n" +
                "    end.setHours(23, 59, 59, 999);\n" +
                "    */\n" +
                "    //**************************************************************************************\n" +
                "\n" +
                "    var thingTypeCodeGIV = \"GIV\";\n" +
                "    var thingTypeCodeItem = \"Item\";\n" +
                "    var thingTypeCodeProduct = \"Products\";\n" +
                "\n" +
                "    var departmentUdfInItem = \"K_DepartmentName.value\";\n" +
                "    var departmentUdfInProduct = \"Department.value\";\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "\n" +
                "    var department = \"\";\n" +
                "    var storeID = \"\";\n" +
                "\n" +
                "    var Zone = (options.filters && options.filters[\"Zone\"]) ? options.filters[\"Zone\"] : ''; // Format zone : 207_Front_Intimates\n" +
                "\n" +
                "    if (Zone != undefined && Zone != null) {\n" +
                "        zones = Zone.split(\"_\");\n" +
                "        if (zones.length === 3) {\n" +
                "            department = zones[2];\n" +
                "            storeID = zones[0];\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    // Get SKU list of GIV\n" +
                "    //**************************************************************************************\n" +
                "\n" +
                "    var queryMatchGiv = {};\n" +
                "    queryMatchGiv[\"thingTypeCode\"] = thingTypeCodeGIV;\n" +
                "    queryMatchGiv[\"GIV_SOURCE.value\"] = \"START\";\n" +
                "    queryMatchGiv[\"GIVDate.value\"] = { $gte: start, $lt: end };\n" +
                "    queryMatchGiv[\"ONHAND.value\"] = { $gt: 0 };\n" +
                "    queryMatchGiv[\"SHIPNODE_KEY.value\"] = storeID;\n" +
                "\n" +

                "    var givSKUS = {};\n" +
                "    var skus = [];\n" +
                "    \n" +
                "\n" +
                "        givSKUS[x.ITEM_ID.value] = {};\n" +
                "        givSKUS[x.ITEM_ID.value][\"ONHAND\"] = parseInt(x.ONHAND.value);\n" +
                "        skus.push(x.ITEM_ID.value)\n" +
                "\n" +
                "    });\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    // Get SKU list of Item\n" +
                "    //**************************************************************************************\n" +
                "\n" +
                "    var queryMatchItem = {};\n" +
                "    queryMatchItem[\"thingTypeCode\"] = thingTypeCodeItem;\n" +
                "    queryMatchItem[departmentUdfInItem] = department;\n" +
                "    queryMatchItem[\"zone.value.facilityMap\"] = storeID;\n" +
                "    queryMatchItem[\"EPC_GS1KEY.value\"] = { $ne: \"00000000\" }; // Not SGTIN\n" +
                "    queryMatchItem[\"K_SKUNumber.value\"] = { $exists: true, $ne: null, $in: skus };\n" +
                "\n" +
                "    var queryGroupItem = {\n" +
                "        _id: {\n" +
                "            sku: '$K_SKUNumber.value'\n" +
                "        },\n" +
                "        brand: { $first: '$K_Brand.value' },\n" +
                "        skuDescription: { $first: '$K_SKUDescription.value' },\n" +
                "        quantity: { $sum: 1 },\n" +
                "        epcs: { $addToSet: '$serialNumber' }\n" +
                "    };\n" +
                "\n" +
                "    var itemSKUS = {};\n" +
                "\n" +
                "    db.things.aggregate([\n" +
                "        { $match: queryMatchItem },\n" +
                "        { $group: queryGroupItem }\n" +
                "    ]).forEach(function(x) {\n" +
                "\n" +
                "        itemSKUS[x._id.sku] = {};\n" +
                "        itemSKUS[x._id.sku][\"epcs\"] = x.epcs.join('|');\n" +
                "\n" +
                "    })\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    // Get SKU list of Products\n" +
                "    //*************************************************************************************\n" +
                "\n" +
                "    var queryMatchProducts = {};\n" +
                "    queryMatchProducts[\"thingTypeCode\"] = thingTypeCodeProduct;\n" +
                "    queryMatchProducts[\"UPCNumber.value\"] = { $exists: true, $ne: null };\n" +
                "    queryMatchProducts[\"Brand.value\"] = { $exists: true, $ne: null };\n" +
                "    queryMatchProducts[departmentUdfInProduct] = department;\n" +
                "    queryMatchProducts[\"SKUNumber.value\"] = { $exists: true, $ne: null, $in: skus };\n" +
                "\n" +
                "    var queryGroupProducts = {\n" +
                "        _id: {\n" +
                "            sku: '$SKUNumber.value'\n" +
                "        },\n" +
                "        brand: { $first: '$Brand.value' },\n" +
                "        skuDescription: { $first: '$SKUDescription.value' },\n" +
                "\n" +
                "        quantity: { $sum: 1 },\n" +
                "        upcs: { $addToSet: '$UPCNumber.value' }\n" +
                "    };\n" +
                "\n" +
                "    var productsSKUS = {};\n" +
                "\n" +
                "    db.things.aggregate([\n" +
                "        { $match: queryMatchProducts },\n" +
                "        { $group: queryGroupProducts }\n" +
                "    ]).forEach(function(x) {\n" +
                "\n" +
                "        productsSKUS[x._id.sku] = {};\n" +
                "        productsSKUS[x._id.sku][\"upcs\"] = x.upcs.join('|');\n" +
                "        productsSKUS[x._id.sku][\"brand\"] = x.brand;\n" +
                "        productsSKUS[x._id.sku][\"brandQty\"] = 0;\n" +
                "        productsSKUS[x._id.sku][\"skuDescription\"] = x.skuDescription;\n" +
                "        productsSKUS[x._id.sku][\"qtyOnHand\"] = 0;\n" +
                "\n" +
                "    })\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    // Get Cycle Count\n" +
                "    //*************************************************************************************\n" +
                "\n" +
                "    var queryMatchCycleCount = {};\n" +
                "    queryMatchCycleCount[\"thingTypeCode\"] = thingTypeCodeItem;\n" +
                "    queryMatchCycleCount[\"K_UPCNumber.value\"] = { $exists: true, $ne: null };\n" +
                "    queryMatchCycleCount[\"K_Brand.value\"] = { $exists: true, $ne: null };\n" +
                "    queryMatchCycleCount[departmentUdfInItem] = department;\n" +
                "    queryMatchCycleCount[\"zone.value.facilityMap\"] = storeID;\n" +
                "    queryMatchCycleCount[\"CycleCountDate.value\"] = { $gte: start.getTime(), $lt: end.getTime() };\n" +
                "    queryMatchCycleCount[\"K_SKUNumber.value\"] = { $exists: true, $ne: null, $in: skus };\n" +
                "\n" +
                "    var queryGroupCycleCount = {\n" +
                "        _id: {\n" +
                "            sku: '$K_SKUNumber.value'\n" +
                "        },\n" +
                "        quantity: { $sum: 1 }\n" +
                "    };\n" +
                "\n" +
                "\n" +
                "    var lastCycleCount = {};\n" +
                "    db.things.aggregate([\n" +
                "        { $match: queryMatchCycleCount },\n" +
                "        { $group: queryGroupCycleCount }\n" +
                "    ]).forEach(function(x) {\n" +
                "\n" +
                "        lastCycleCount[x._id.sku] = {};\n" +
                "        lastCycleCount[x._id.sku].quantity = x.quantity;\n" +
                "\n" +
                "    })\n" +
                "\n" +
                "    //**************************************************************************************\n" +
                "    // Merge function to get result\n" +
                "    //*************************************************************************************\n" +
                "\n" +
                "    function Merge(productsSKUS, itemSKUS, givSKUS, lastCycleCount) {\n" +
                "        var total = {};\n" +
                "\n" +
                "        for (var sku in productsSKUS) {\n" +
                "            if (givSKUS.hasOwnProperty(sku)) {\n" +
                "                productsSKUS[sku][\"qtyOnHand\"] = givSKUS[sku][\"ONHAND\"];\n" +
                "\n" +
                "            }\n" +
                "            if (lastCycleCount.hasOwnProperty(sku) && givSKUS.hasOwnProperty(sku)) {\n" +
                "                productsSKUS[sku][\"qtyOnHand\"] = givSKUS[sku][\"ONHAND\"] - lastCycleCount[sku].quantity;\n" +
                "\n" +
                "            }\n" +
                "            if (itemSKUS.hasOwnProperty(sku)) {\n" +
                "                productsSKUS[sku][\"epcs\"] = itemSKUS[sku][\"epcs\"];\n" +
                "\n" +
                "            } else {\n" +
                "                productsSKUS[sku][\"epcs\"] = \"\";\n" +
                "            }\n" +
                "\n" +
                "            var groupBy = productsSKUS[sku][\"brand\"].toString();\n" +
                "            if (total.hasOwnProperty(groupBy)) {\n" +
                "                if (productsSKUS[sku][\"qtyOnHand\"] > 0) {\n" +
                "                    total[groupBy] = total[groupBy] + productsSKUS[sku][\"qtyOnHand\"];\n" +
                "                }\n" +
                "            } else {\n" +
                "                total[groupBy] = productsSKUS[sku][\"qtyOnHand\"];\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        var result = [];\n" +
                "        for (var sku in productsSKUS) {\n" +
                "            if (total.hasOwnProperty(productsSKUS[sku][\"brand\"])) {\n" +
                "                productsSKUS[sku][\"brandQty\"] = total[productsSKUS[sku][\"brand\"]];\n" +
                "            }\n" +
                "            if (productsSKUS[sku][\"qtyOnHand\"] > 0) {\n" +
                "\n" +
                "                result.push([productsSKUS[sku][\"brand\"], productsSKUS[sku][\"brandQty\"], sku, productsSKUS[sku][\"skuDescription\"], productsSKUS[sku][\"qtyOnHand\"], productsSKUS[sku][\"upcs\"], productsSKUS[sku][\"epcs\"]]);\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        result.sort(function(a, b) {\n" +
                "            return (b[1] == a[1]) ? (parseFloat(b[4]) - parseFloat(a[4])) : (parseFloat(b[1]) - parseFloat(a[1]));\n" +
                "        })\n" +
                "\n" +
                "        return result;\n" +
                "    }\n" +
                "\n" +
                "    var finalResult = Merge(productsSKUS, itemSKUS, givSKUS, lastCycleCount);\n" +
                "\n" +
                "    /***************************** PAGINATION ************************/\n" +
                "    table.totalRows = finalResult.length;\n" +
                "    var skip = options.pageNumber > 0 ? options.pageSize * (options.pageNumber - 1) : 0;\n" +
                "    var limit = (options.pageSize != -1 && options.pageSize !== 0) ? options.pageSize : table.totalRows;\n" +
                "    /*****************************************************************/\n" +
                "\n" +
                "    db.getSisterDB(\"admin4\").shutdownServer();\n" +

                "    var data = finalResult.slice(skip, (limit + skip));\n" +
                "\n" +
                "    table.data = data;\n" +
                "\n" +
                "    return table;\n" +
                "}";
    }
}