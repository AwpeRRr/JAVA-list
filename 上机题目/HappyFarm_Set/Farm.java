import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Farm {
    private ArrayList<ArrayList<FarmObject>> farmRows = new ArrayList<ArrayList<FarmObject>>();
    private Set<Integer> idSet = new HashSet<Integer>();

    public void initFarm(int rowCount) {
        farmRows.clear();
        idSet.clear();
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
        return idSet.contains(id);
    }

    public boolean addObject(FarmObject obj, int rowIndex) {
        if (obj == null || !isValidRowIndex(rowIndex) || idSet.contains(obj.getId())) {
            return false;
        }
        farmRows.get(rowIndex).add(obj);
        idSet.add(obj.getId());
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
        FarmObject removed = farmRows.get(rowIndex).remove(positionIndex);
        idSet.remove(removed.getId());
        return removed;
    }

    public void clearFarm() {
        for (ArrayList<FarmObject> row : farmRows) {
            row.clear();
        }
        idSet.clear();
    }

    public int getTotalCount() {
        return idSet.size();
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
