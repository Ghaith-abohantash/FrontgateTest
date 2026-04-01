package utils;

public class TestDataWriter {

    public static final String FILE_PATH = "src/test/resources/testdata.xlsx";

    public static final String VALID_EMAIL = "ghaith.a.hantash@gmail.com";
    public static final String VALID_PASSWORD = "Ghaith123-";
    public static final String WRONG_PASSWORD = "12356646876";

    // ===============================================
    // ===============================================
    /*
    public static void createExcelIfNotExists() throws Exception {
        File file = new File(FILE_PATH);
        if (file.exists()) return;

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("LoginCases");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("email");
        header.createCell(1).setCellValue("password");

        Row r1 = sheet.createRow(1);
        r1.createCell(0).setCellValue("invalid-email");
        r1.createCell(1).setCellValue("");

        Row r2 = sheet.createRow(2);
        r2.createCell(0).setCellValue(VALID_EMAIL);
        r2.createCell(1).setCellValue(WRONG_PASSWORD);

        Row r3 = sheet.createRow(3);
        r3.createCell(0).setCellValue(VALID_EMAIL);
        r3.createCell(1).setCellValue(VALID_PASSWORD);

        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
    */
}