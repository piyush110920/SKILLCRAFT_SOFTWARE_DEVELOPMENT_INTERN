import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SKILLCRAFT_SD_03 {
    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton solveButton;
    public SKILLCRAFT_SD_03() {
        JFrame frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new JTextField();
                cells[r][c].setBounds(30 * c + 20, 30 * r + 20, 30, 30);
                frame.add(cells[r][c]);
            }
        }
        solveButton = new JButton("Solve");
        solveButton.setBounds(20, 300, 100, 30);
        frame.add(solveButton);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });
        frame.setVisible(true);
    }
    private void solveSudoku() {
        int[][] grid = new int[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                String text = cells[r][c].getText();
                grid[r][c] = text.isEmpty() ? 0 : Integer.parseInt(text);
            }
        }
        if (solve(grid)) {
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    cells[r][c].setText(grid[r][c] == 0 ? "" : String.valueOf(grid[r][c]));
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No solution exists.");
        }
    }
    private boolean solve(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solve(grid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (grid[row][x] == num || grid[x][col] == num) {
                return false;
            }
        }
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int r = 0; r < 3; r++) {
            for (int d = 0; d < 3; d++) {
                if (grid[r + startRow][d + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SKILLCRAFT_SD_03());
    }
}