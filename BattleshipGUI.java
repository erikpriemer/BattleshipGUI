import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BattleshipGUI extends JFrame
{
    JButton[][] myBoard;
    JButton[][] opponentBoard;
    int fieldSize;

    BattleshipGUI(int size) {
        this.fieldSize = size;
        myBoard = new JButton[fieldSize][fieldSize];
        opponentBoard = new JButton[fieldSize][fieldSize];

        // Every JPanel that gets placed into the battleshipFrame
        JPanel boardPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        //setting the size of each JPanel
        boardPanel.setPreferredSize(new Dimension(100, 100));
        bottomPanel.setPreferredSize(new Dimension(100, 350));
        leftPanel.setPreferredSize(new Dimension(200, 100));
        rightPanel.setPreferredSize(new Dimension(200, 100));

        //changing the background color of each JPanel
        boardPanel.setBackground(new Color(210, 210, 210));
        bottomPanel.setBackground(new Color(210, 210, 210));
        leftPanel.setBackground(new Color(224, 224, 224));
        rightPanel.setBackground(new Color(224, 224, 224));

        //the boardPanel needs to have a GridLayout, so the board is resizable
        boardPanel.setLayout(new GridLayout());
        leftPanel.setLayout(new GridBagLayout());
        bottomPanel.setLayout(new BorderLayout());


        //create myBoard
        JPanel board1 = new JPanel(new GridLayout(fieldSize, fieldSize));
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                //The background of the buttons is darkblue and the border is black
                myBoard[i][j] = new JButton();
                myBoard[i][j].setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
                myBoard[i][j].setOpaque(true);
                setImageIcon("Resources/water.png", myBoard[i][j], calcSize(), calcSize());
                board1.add(myBoard[i][j]);
            }
        }
        //build a green border around board1
        Border border = board1.getBorder();
        Border margin = new LineBorder(new Color(77, 173, 91),10);
        board1.setBorder(new CompoundBorder(border, margin));
        boardPanel.add(board1, BorderLayout.SOUTH);


        //create opponentBoard
        JPanel board2 = new JPanel(new GridLayout(fieldSize, fieldSize));
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                //The background of the buttons is darkblue and the border is black
                opponentBoard[i][j] = new JButton();
                opponentBoard[i][j].setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
                opponentBoard[i][j].setOpaque(true);
                setImageIcon("Resources/water.png", opponentBoard[i][j], calcSize(), calcSize());
                board2.add(opponentBoard[i][j]);
            }
        }
        //board2 has a red border
        Border border2 = board2.getBorder();
        Border margin2 = new LineBorder(new Color(210, 91, 91),10);
        board2.setBorder(new CompoundBorder(border2, margin2));
        boardPanel.add(board2, BorderLayout.NORTH);



        //create the shoot button and place it in the middle of the leftPanel
        JButton shootButton = new JButton("Shoot");
        shootButton.setPreferredSize(new Dimension(100, 50));
        shootButton.setForeground(Color.gray);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(shootButton, gbc);


        //build the Bottom Panel, where you can choose the ships
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setPreferredSize(new Dimension(100, 300));
        buttonPanel2.setBackground(new Color(210, 210, 210));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.fill = GridBagConstraints.VERTICAL;

        //Add a description to the Ship section
        JLabel shipText = new JLabel("Add ships to Battlefield!");
        bottomPanel.add(shipText, BorderLayout.CENTER);


        //add Ship buttons to buttonPanel2
        if(6 < fieldSize && fieldSize < 10)
        {
            addCarrierButton(buttonPanel2,gbc2);
            addBattleshipButton(buttonPanel2,gbc2);
            addCruiserButton(buttonPanel2,gbc2);
            addSubmarineButton(buttonPanel2,gbc2);
            addDestroyerButton(buttonPanel2,gbc2);
        }
        if(10 <= fieldSize && fieldSize < 20)
        {
            addCarrierButton(buttonPanel2,gbc2);
            addBattleshipButton(buttonPanel2,gbc2);
            addCruiserButton(buttonPanel2,gbc2);
            addSubmarineButton(buttonPanel2,gbc2);
            addDestroyerButton(buttonPanel2,gbc2);
            addCarrierButton(buttonPanel2,gbc2);
            addBattleshipButton(buttonPanel2,gbc2);
            addCruiserButton(buttonPanel2,gbc2);
            addSubmarineButton(buttonPanel2,gbc2);
            addDestroyerButton(buttonPanel2,gbc2);
        }
        bottomPanel.add(buttonPanel2, BorderLayout.SOUTH);


        //build the JMenuBar with different Items
        JMenuBar bar = new JMenuBar();
        {
            //Menu
            JMenu menuSettings = new JMenu("Menu");
            {
                //Save Game
                JMenuItem menuItemSaveGame = new JMenuItem("Save Game");
                menuItemSaveGame.addActionListener(
                        e -> {System.out.println("Save Game");}
                );
                menuSettings.add(menuItemSaveGame);
                menuSettings.addSeparator();

                //Exit
                JMenuItem menuItemExit = new JMenuItem("Exit");
                menuItemExit.addActionListener(
                        e -> {System.exit(0);}
                );
                menuSettings.add(menuItemExit);
            }
            bar.add(menuSettings);


            //Game
            JMenu gameSetting = new JMenu("Game");
            {
                //Online Game
                JMenuItem menuItemOnlineGame = new JMenuItem("Online Game");
                menuItemOnlineGame.addActionListener(
                        e -> {System.out.println("Online Game");}
                );
                gameSetting.add(menuItemOnlineGame);
                gameSetting.addSeparator();

                //Offline Game
                JMenuItem menuItemOfflineGame = new JMenuItem("Offline Game");
                menuItemOfflineGame.addActionListener(
                        e -> {System.out.println("Offline Game");}
                );
                gameSetting.add(menuItemOfflineGame);
                gameSetting.addSeparator();
                gameSetting.addSeparator();

                //Load Game
                JMenuItem menuItemLoadGame = new JMenuItem("Load Game");
                menuItemLoadGame.addActionListener(
                        (e -> {
                            System.out.println("Load Game");
                        })
                );
                gameSetting.add(menuItemLoadGame);
            }
            bar.add(gameSetting);
        }
        //sett the size of the bar
        bar.setPreferredSize(new Dimension(10, 30));



        //create main window, it uses BorderLayout, so the boardPanel and the other ones get arranged properly
        JFrame battleshipFrame = new JFrame("Battleship");
        battleshipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleshipFrame.setLayout(new BorderLayout());
        battleshipFrame.setSize(1500, 1500);
        battleshipFrame.setVisible(true);
        battleshipFrame.setResizable(true);

        //add every JPanel to the battleshipFrame
        battleshipFrame.add(bar, BorderLayout.NORTH);
        battleshipFrame.add(bottomPanel, BorderLayout.SOUTH);
        battleshipFrame.add(boardPanel, BorderLayout.CENTER);
        battleshipFrame.add(leftPanel, BorderLayout.WEST);
        battleshipFrame.add(rightPanel, BorderLayout.EAST);



        //Test
        addCarrier(6, 0);
        addBattleship(3, 5);
        addCruiser(8, 2);
        addSubmarine(8, 7);
        addDestroyer(1, 1);
        colorHitMyShip(6, 1);

        colorMissOpponentShip(6, 8);
        colorMissOpponentShip(3, 5);
        colorHitOpponentShip(8, 1);
        colorHitOpponentShip(8, 2);
        colorHitOpponentShip(8, 3);
        colorHitOpponentShip(8, 4);


    }

    //Create Buttons for each Ship with its Icon and proper size-----------------------------------------------
    private void addCarrierButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(300, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship6.png", ship1, 320, 70);
    }

    private void addBattleshipButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(250, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship5.png", ship1, 270, 70);
    }

    private void addCruiserButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(200, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship4.png", ship1, 220, 70);
    }

    private void addSubmarineButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(150, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship3.png", ship1, 170, 70);
    }

    private void addDestroyerButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(100, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship2.png", ship1, 120, 70);
    }
    //Create Buttons for each Ship with its Icon and proper size-----------------------------------------------



    //functions that change the board Buttons if ship gets hit/missed -----------------------------------------
    private void colorHitMyShip(int i, int j)
    {
        setImageIcon("Resources/hitIcon.png", myBoard[i][j], calcSize(), calcSize());
        myBoard[i][j].setBackground(new Color(165, 25, 25));
    }

    private void colorMissMyShip(int i, int j)
    {
        setImageIcon("Resources/miss.png", myBoard[i][j], calcSize(), calcSize());
        myBoard[i][j].setBackground(Color.RED);
    }

    private void colorHitOpponentShip(int i, int j)
    {
        setImageIcon("Resources/hitOpponent.png", opponentBoard[i][j], calcSize(), calcSize());
        opponentBoard[i][j].setBackground(new Color(165, 25, 25));
    }

    private void colorMissOpponentShip(int i, int j)
    {
        setImageIcon("Resources/miss.png", opponentBoard[i][j], calcSize(), calcSize());
        opponentBoard[i][j].setBackground(Color.RED);
    }
    //functions that change the board Buttons if ship gets hit/missed -----------------------------------------



    //Create ships on board--------------------------------------------------------------------------------------------------
    private void addCarrier(int row, int start)
    {
        setImageIcon("Resources/ship6/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
        setImageIcon("Resources/ship6/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
        setImageIcon("Resources/ship6/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
        setImageIcon("Resources/ship6/row-1-column-4.png", myBoard[row][start+3], calcSize(), calcSize());
        setImageIcon("Resources/ship6/row-1-column-5.png", myBoard[row][start+4], calcSize(), calcSize());
        setImageIcon("Resources/ship6/row-1-column-6.png", myBoard[row][start+5], calcSize(), calcSize());
    }

    private void addBattleship(int row, int start)
    {

        setImageIcon("Resources/ship5/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
        setImageIcon("Resources/ship5/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
        setImageIcon("Resources/ship5/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
        setImageIcon("Resources/ship5/row-1-column-4.png", myBoard[row][start+3], calcSize(), calcSize());
        setImageIcon("Resources/ship5/row-1-column-5.png", myBoard[row][start+4], calcSize(), calcSize());
    }

    private void addCruiser(int row, int start)
    {
        setImageIcon("Resources/ship4/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
        setImageIcon("Resources/ship4/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
        setImageIcon("Resources/ship4/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
        setImageIcon("Resources/ship4/row-1-column-4.png", myBoard[row][start+3], calcSize(), calcSize());
    }

    private void addSubmarine(int row, int start) {
        setImageIcon("Resources/ship3/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
        setImageIcon("Resources/ship3/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
        setImageIcon("Resources/ship3/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
    }

    private void addDestroyer(int row, int start)
    {
        setImageIcon("Resources/ship2/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
        setImageIcon("Resources/ship2/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
    }
    //Create ships on board--------------------------------------------------------------------------------------------------



    //helper-Functions----------------------------------------------------------------------------------------
    //function that creates Border
    public Border createBorder(Color color, int top, int left, int bottom, int right)
    {
        Border line = new LineBorder(color);
        Border margin = new EmptyBorder(top, left, bottom, right);
        return new CompoundBorder(line, margin);
    }

    //function that sets Image Icon of JButton
    public void setImageIcon(String source, JButton button, int width, int height)
    {
        try {
            Image img = ImageIO.read(getClass().getResource(source));
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(dimg));
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
    //helper-Functions----------------------------------------------------------------------------------------

    private int calcSize()
    {
        return 600/fieldSize;
    }


    public static void main(String[] args) {
        new BattleshipGUI(10);
    }
}
