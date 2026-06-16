package HappyFarmTest_ArrayList_4th;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Farm {
    private ArrayList<ArrayList<FarmObject>> farmRows = new ArrayList<ArrayList<FarmObject>>();

    public void initFarm(int rowCount) {
        farmRows.clear();
        for (int i = 0; i < rowCount; i++) {
            farmRows.add(new ArrayList<FarmObject>());
        }
    }

    public int getRowCount() {
        return farmRows.size();
    }

    public int getRowSize(int rowIndex) {
        if (!isValidRowIndex(rowIndex)) {
            return -1;
        }
        return farmRows.get(rowIndex).size();
    }

    public boolean isValidRowIndex(int rowIndex) {
        return rowIndex >= 0 && rowIndex < farmRows.size();
    }

    public boolean isValidPosition(int rowIndex, int positionIndex) {
        return isValidRowIndex(rowIndex)
                && positionIndex >= 0
                && positionIndex < farmRows.get(rowIndex).size();
    }

    public boolean hasId(int id) {
        for (ArrayList<FarmObject> row : farmRows) {
            for (FarmObject obj : row) {
                if (obj.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addObject(FarmObject obj, int rowIndex) {
        if (obj == null || !isValidRowIndex(rowIndex)) {
            return false;
        }
        farmRows.get(rowIndex).add(obj);
        return true;
    }

    public FarmObject getObject(int rowIndex, int positionIndex) {
        if (!isValidPosition(rowIndex, positionIndex)) {
            return null;
        }
        return farmRows.get(rowIndex).get(positionIndex);
    }

    public FarmObject removeObject(int rowIndex, int positionIndex) {
        if (!isValidPosition(rowIndex, positionIndex)) {
            return null;
        }
        return farmRows.get(rowIndex).remove(positionIndex);
    }

    public void clearFarm() {
        for (ArrayList<FarmObject> row : farmRows) {
            row.clear();
        }
    }

    public int getTotalCount() {
        int total = 0;
        for (ArrayList<FarmObject> row : farmRows) {
            total += row.size();
        }
        return total;
    }

    public void saveToFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("ROWS\t" + getRowCount());
            writer.newLine();

            for (int rowIndex = 0; rowIndex < farmRows.size(); rowIndex++) {
                ArrayList<FarmObject> row = farmRows.get(rowIndex);
                for (FarmObject obj : row) {
                    writer.write("OBJECT\t" + rowIndex
                            + "\t" + obj.getClass().getSimpleName()
                            + "\t" + obj.getId()
                            + "\t" + escapeText(obj.getName()));
                    writer.newLine();
                }
            }
        }
    }

    public boolean loadFromFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            return false;
        }

        ArrayList<ArrayList<FarmObject>> loadedRows = new ArrayList<ArrayList<FarmObject>>();
        Set<Integer> loadedIds = new HashSet<Integer>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header == null) {
                throw new IOException("存档文件为空。");
            }

            String[] headerFields = header.split("\t", -1);
            if (headerFields.length != 2 || !headerFields[0].equals("ROWS")) {
                throw new IOException("存档首行格式错误。");
            }

            int rowCount = parseInteger(headerFields[1], 1, "农场行数");
            if (rowCount <= 0) {
                throw new IOException("农场行数必须大于 0。");
            }
            for (int i = 0; i < rowCount; i++) {
                loadedRows.add(new ArrayList<FarmObject>());
            }

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().length() == 0) {
                    continue;
                }

                String[] fields = line.split("\t", 5);
                if (fields.length != 5 || !fields[0].equals("OBJECT")) {
                    throw new IOException("第 " + lineNumber + " 行对象格式错误。");
                }

                int rowIndex = parseInteger(fields[1], lineNumber, "行号");
                if (rowIndex < 0 || rowIndex >= rowCount) {
                    throw new IOException("第 " + lineNumber + " 行的对象位置超出农场范围。");
                }

                int id = parseInteger(fields[3], lineNumber, "编号");
                if (id <= 0 || !loadedIds.add(id)) {
                    throw new IOException("第 " + lineNumber + " 行的对象编号无效或重复。");
                }

                String name = unescapeText(fields[4], lineNumber);
                if (name.trim().length() == 0) {
                    throw new IOException("第 " + lineNumber + " 行的对象名称为空。");
                }

                FarmObject obj = createObjectByClassName(fields[2], id, name, lineNumber);
                loadedRows.get(rowIndex).add(obj);
            }
        }

        farmRows.clear();
        farmRows.addAll(loadedRows);
        return true;
    }

    private int parseInteger(String value, int lineNumber, String fieldName) throws IOException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IOException("第 " + lineNumber + " 行的" + fieldName + "不是有效整数。");
        }
    }

    private FarmObject createObjectByClassName(String className, int id, String name, int lineNumber)
            throws IOException {
        switch (className) {
            case "Wheat":
                return new Wheat(id, name);
            case "Corn":
                return new Corn(id, name);
            case "Chicken":
                return new Chicken(id, name);
            case "Cow":
                return new Cow(id, name);
            default:
                throw new IOException("第 " + lineNumber + " 行包含未知对象类型: " + className);
        }
    }

    private String escapeText(String text) {
        return text.replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }

    private String unescapeText(String text, int lineNumber) throws IOException {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (current != '\\') {
                result.append(current);
                continue;
            }

            if (i + 1 >= text.length()) {
                throw new IOException("第 " + lineNumber + " 行的名称转义格式错误。");
            }

            char next = text.charAt(++i);
            switch (next) {
                case '\\':
                    result.append('\\');
                    break;
                case 't':
                    result.append('\t');
                    break;
                case 'r':
                    result.append('\r');
                    break;
                case 'n':
                    result.append('\n');
                    break;
                default:
                    throw new IOException("第 " + lineNumber + " 行的名称转义格式错误。");
            }
        }
        return result.toString();
    }

    public void printAllObjects() {
        if (farmRows.isEmpty()) {
            System.out.println("农场还没有初始化，请先创建农场行。");
            return;
        }

        System.out.println("--- 当前农场对象列表 ---");
        for (int rowIndex = 0; rowIndex < farmRows.size(); rowIndex++) {
            ArrayList<FarmObject> row = farmRows.get(rowIndex);
            System.out.println("第 " + rowIndex + " 行，共 " + row.size() + " 个对象");

            if (row.isEmpty()) {
                System.out.println("  （空）");
            } else {
                for (int positionIndex = 0; positionIndex < row.size(); positionIndex++) {
                    System.out.println("  位置 " + positionIndex + " -> " + row.get(positionIndex));
                }
            }
        }
        System.out.println("对象总数: " + getTotalCount());
        System.out.println("------------------------");
    }

    public void searchByName(String name) {
        boolean found = false;

        for (int rowIndex = 0; rowIndex < farmRows.size(); rowIndex++) {
            ArrayList<FarmObject> row = farmRows.get(rowIndex);
            for (int positionIndex = 0; positionIndex < row.size(); positionIndex++) {
                FarmObject obj = row.get(positionIndex);
                if (obj.getName().equals(name)) {
                    if (!found) {
                        System.out.println("--- 查找结果 ---");
                    }
                    System.out.println("编号: " + obj.getId()
                            + "\t类型: " + obj.getType()
                            + "\t名称: " + obj.getName()
                            + "\t位置: 第 " + rowIndex + " 行，第 " + positionIndex + " 个位置");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("未找到名称为 [" + name + "] 的对象。");
        }
    }
}
