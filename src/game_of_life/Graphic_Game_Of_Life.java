package game_of_life;

/**
 * 
 *      Again, renaming this stuff seems to be a precarious proposition.  
 *      So, "AfterPanelRedo" it is.
 * 
 */




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Graphic_Game_Of_Life extends JPanel
{
    Game_Of_Life gol;
    Border theBorder;
    JEditorPane instructions1;
    private JCheckBox squareBoard;
    Timer nextGenTimer;
    Font myFont;
    
    Boolean squareGrid;
    Boolean work;
    Boolean alt = false;
    Boolean shift = false;
    Boolean colorMixup = false;
    Boolean cycleColors = false;
    Boolean[][] gridStateInfo;    
    
    private String[] patternChoices;
    private String[] sizeChoices;
    
    GridPanel gridPanel;
    JPanel gridContainer;
    JPanel controlsAndInstructionsPanel;
    JPanel controlsPanel;
    JPanel instructionsPanel;
    JPanel sizePanel;
    JPanel patternPanel;
    JPanel timerPanel;
    JPanel colorControlsPanel;
    JPanel colorChooserPanel;
    JPanel timerButtonsPanel;
    JPanel genButtonsPanel;
    JPanel colorRadioPanel;    
    private JPanel genCounterTextPanel;
    private JPanel patternSetButtons;    
    
    private JComboBox cellSizeDropDown;
    private JComboBox rows;
    private JComboBox cols;
    private JComboBox patternDropDown;
    
    private JButton iNeed;
    private JButton activateCellSize;
    private JButton placePattern;
    private JButton clearGrid;
    private JButton startStop;
    private JButton oneGeneration;
    
    private JButton[] colorButton;
    JButton[][] gridSquare_Button;
    
    private JRadioButton slowTime;
    private JRadioButton medTime;
    private JRadioButton fastTime;
    private JRadioButton colorMix;
    private JRadioButton colorCycle;
    private JRadioButton colorNeither;
    
    private ButtonGroup timerRadioGroup;
    private ButtonGroup colorRadioGroup;
    
    private JTextArea genCounterTextArea;
    private JTextArea genCounterTextArea2;
    private JTextArea cellSizePrompt;
    
    Color chosenColor;
    Color myPink;
    Color myBlue;
    Color myGreen;
    Color myOrange;
    Color myYellow;
    Color myPurple;
    Color myRed;
    Color myLightBlue;
    
    int cellSize;
    int numberRows;
    int numberCols;
    int gridWidth;
    int gridHeight;
    private int genCounter;
    private String popd;//do I need these????
    private String empty;
    private String genCounterString;
   
    

    public Graphic_Game_Of_Life(int cellSize, int gridContainerWidth, int gridContainerHeight)
    {
        myFont = new Font("Segoe UI Semibold", Font.BOLD, 24);
        this.cellSize = cellSize;
        gridWidth = gridContainerWidth;
        gridHeight = gridContainerHeight;
        numberRows = gridWidth / cellSize;//could change 
        numberCols = gridHeight / cellSize;
        makeColorValues();
        theBorder = BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createLoweredBevelBorder()),
                BorderFactory.createLoweredBevelBorder());
        chosenColor = myBlue;

        gol = new Game_Of_Life(numberRows, numberCols);
        gridSquare_Button = new JButton[numberRows][numberCols];
        nextGenTimer = new Timer(200, new Timer_Listener());
        popd = "%";
        empty = "`";
        makeColorButtons();
        makeSmallPanels();
        makeLargePanels();
        initializeGridButtons();
        addGridButtons();
    }


    private void initializeGridButtons()
    {
        Mouse_Activity_Listener test = new Mouse_Activity_Listener();
        gridStateInfo = gol.getGridStateInfo();
        {
            for (int i = 0; i < numberRows; i++)
            {
                for (int j = 0; j < numberCols; j++)
                {
                    gridSquare_Button[i][j] = new JButton();
                    setButtonPopdOrEmpty((gridStateInfo[i][j]) ? popd : empty, gridSquare_Button[i][j]);
                    gridSquare_Button[i][j].setBorderPainted(false);
                    gridSquare_Button[i][j].setSize(cellSize - 1, cellSize - 1);
                    gridSquare_Button[i][j].setFocusPainted(false);
                    gridSquare_Button[i][j].addActionListener(new GridSquare_Listener());
                    gridSquare_Button[i][j].addMouseListener(test);
                    gridSquare_Button[i][j].addKeyListener(test);
                }
            }
        }
    }

    private void removeGridButtons()
    {
        for (int i = 0; i < numberRows; i++)
        {
            for (int j = 0; j < numberCols; j++)
            {

                gridPanel.remove(gridSquare_Button[i][j]);
                gridPanel.repaint();
            }
        }
    }

    private void addGridButtons()
    {
        int gridX = 1;
        int gridY = 1;

        for (int i = 0; i < numberRows; i++)
        {
            for (int j = 0; j < numberCols; j++)
            {
                gridSquare_Button[i][j].setLocation(gridX, gridY);
                gridPanel.add(gridSquare_Button[i][j]);
                gridX += cellSize;
            }
            gridX = 1;
            gridY += cellSize;
        }
    }

    private void makeColorButtons()
    {
        colorButton = new JButton[8];

        for (int i = 0; i < 8; i++)
        {
            colorButton[i] = new JButton();
            colorButton[i].addActionListener(new Color_Listener());
            colorButton[i].setFocusPainted(false);
            colorButton[i].setBorder(theBorder);
            colorButton[i].setBorderPainted(false);

            switch (i)
            {
                case 0:
                    colorButton[i].setBackground(myBlue);
                    colorButton[i].setBorderPainted(true);
                    break;
                case 1:
                    colorButton[i].setBackground(myYellow);
                    break;
                case 2:
                    colorButton[i].setBackground(myGreen);
                    break;
                case 3:
                    colorButton[i].setBackground(myLightBlue);
                    break;
                case 4:
                    colorButton[i].setBackground(myOrange);
                    break;
                case 5:
                    colorButton[i].setBackground(myPink);
                    break;
                case 6:
                    colorButton[i].setBackground(myPurple);
                    break;
                case 7:
                    colorButton[i].setBackground(myRed);
                    break;
            }
        }
    }

    private void makeColorValues()
    {
        myPink = new Color(255, 0, 181);
        myBlue = new Color(25, 0, 255);
        myLightBlue = new Color(0, 187, 255);
        myYellow = new Color(238, 255, 0);
        myOrange = new Color(232, 114, 0);
        myRed = new Color(232, 19, 0);
        myGreen = new Color(0, 255, 31);
        myPurple = new Color(188, 107, 242);
    }

    private void makeSmallPanels()
    {
        makeSizePanel();
        makePatternPanel();
        makeTimerPanel();
        makeColorControlsPanel();
        makeColorChooserPanel();

        iNeed = new JButton("Instructions, please...");
        iNeed.setSize(new Dimension(200, 26));
        iNeed.setFocusPainted(false);
        iNeed.setBackground(Color.DARK_GRAY);
        iNeed.setForeground(Color.WHITE);
        iNeed.setLocation(10, 2);
        iNeed.addActionListener(new Instructions_Listener());

        instructionsPanel = new JPanel();
        instructionsPanel.setLayout(null);
        instructionsPanel.setPreferredSize(new Dimension(1200, 32));
        instructionsPanel.add(iNeed);
        instructionsPanel.setBackground(Color.BLACK);

        controlsPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        controlsPanel.setBackground(Color.BLACK);
        controlsPanel.add(sizePanel);
        controlsPanel.add(patternPanel);
        controlsPanel.add(timerPanel);
        controlsPanel.add(colorControlsPanel);
        controlsPanel.add(colorChooserPanel);
    }

    private String makeInstructions()
    {
        String s = "";        
        s += "<div style = \"color: white; font-size: 18; font-family: Segoe UI Symbol, sans-serif;\">";
        s += "<span style = \"color: lime;\">Controls from left to right:</span><br/><p/>";
        s += "<span style = \"color: aqua;\">To change the size of the little squares:</span><br/>  "
                + "Choose a number from the dropdown menu.&nbsp;&nbsp;  "
                + "The smaller the number, the smaller the squares. &nbsp;&nbsp; \nCheck \"Square Board\" for an equal number "
                + "of rows and columns.&nbsp;&nbsp;"
                + "  The changes take place after you click \"Set.\"  &nbsp;&nbsp;Don't despair if it takes\nseveral seconds.<br/><p/>";
        s += "<span style = \"color: aqua;\">To place a pattern:</span> <br/> Choose a pattern from the dropdown menu, "
                + "then click \"Place Pattern.\""
                + " &nbsp;&nbsp; Placing a new pattern will automatically clear\nwhatever might already be on the board. &nbsp;&nbsp; "
                + "To clear the board yourself, click \"Clear Board.\"<br/><p/>";
        s += "<span style = \"color: aqua;\">To \"draw\" on the board yourself:</span> <br/>a) &nbsp;&nbsp;Click on "
                + "individual squares to toggle them colored or uncolored, or...<br/>"
                + "b)&nbsp;&nbsp; Sweep your mouse over the squares while holding SHIFT to color them, or CTRL to uncolor them.<br/><p/>";
        s += "<span style = \"color: aqua;\">To set the generations in motion: </span><br/> Choose a speed and "
                + "click \"Start.\" &nbsp;&nbsp; "
                + "You can change speeds while it's going.  &nbsp;&nbsp;Alternatively, click\n\"One Generation\" to view generation change one "
                + "at a time.<br/><p/>";
        s += "<span style = \"color: aqua;\">To Colors! </span> <br/>Select the color of the next generation by clicking a color button.  "
                + "&nbsp;&nbsp;When a pattern is placed, it will be the currently selected color.&nbsp;&nbsp;"
                + "Selecting \"Color Mix\" or \"Color Cycle\" is heartily recommended when running generations in motion.";
        s += "</div>";
        return s;
    }
    
    private void makeLargePanels()
    {               
        String instr = makeInstructions();        
        
        instructions1 = new JEditorPane();
        instructions1.setContentType("text/html");
        instructions1.setMargin(new Insets(40, 20, 10, 20));
        instructions1.setSize(1000, 600);
        instructions1.setLocation(50, 10);
        instructions1.setBackground(Color.DARK_GRAY);
        instructions1.setForeground(Color.WHITE);
        instructions1.setText(instr);
        instructions1.setVisible(false);
        
        gridPanel = new GridPanel(cellSize);
        gridPanel.setSize(cellSize * numberCols, 720);
        gridPanel.setLocation(0, 0);
        gridPanel.setBackground(Color.BLACK);
        gridPanel.setLayout(null);
        gridPanel.add(instructions1);

        gridContainer = new JPanel();  //see if you can get rid of this container panel
        gridContainer.setLayout(null);
        gridContainer.setBackground(Color.BLACK);
        gridContainer.setPreferredSize(new Dimension(1200, 780));
        gridContainer.add(gridPanel);

        controlsAndInstructionsPanel = new JPanel(new BorderLayout());
        controlsAndInstructionsPanel.setPreferredSize(new Dimension(1200, 150));        
        controlsAndInstructionsPanel.add(instructionsPanel, BorderLayout.SOUTH);
        controlsAndInstructionsPanel.add(controlsPanel, BorderLayout.NORTH);
        controlsAndInstructionsPanel.setBackground(Color.BLACK);

        this.setLayout(new BorderLayout());
        this.add(gridContainer, BorderLayout.CENTER);
        this.add(controlsAndInstructionsPanel, BorderLayout.SOUTH);
    }

    private void makeSizePanel()
    {
        int size = 10;
        
        sizeChoices = new String[21];
        for (int i = 0; i < 20; i++)
        {
            sizeChoices[i] = "" + size;
            size += 2;
        }
        squareBoard = new JCheckBox("Square Board");
        squareBoard.setOpaque(false);
        squareBoard.setForeground(Color.WHITE);
        
        activateCellSize = new JButton("Set");
        activateCellSize.addActionListener(new Size_Listener());
        
        cellSizePrompt = new JTextArea("Size of Little Squares:");
        
        sizePanel = new JPanel(new FlowLayout());
        sizePanel.setPreferredSize(new Dimension(100, 100));
        
        cellSizePrompt.setPreferredSize(new Dimension(125, 50));
        cellSizePrompt.setMargin(new Insets(15, 0, 0, 0));
        cellSizePrompt.setBackground(Color.BLACK);
        cellSizePrompt.setForeground(Color.WHITE);
        
        cellSizeDropDown = new JComboBox(sizeChoices);
        cellSizeDropDown.setPreferredSize(new Dimension(60, 30));
        cellSizeDropDown.setBackground(Color.DARK_GRAY);
        cellSizeDropDown.setForeground(Color.WHITE);
        cellSizeDropDown.setSelectedIndex(1);

        activateCellSize.setPreferredSize(new Dimension(80, 30));
        activateCellSize.setBackground(Color.DARK_GRAY);
        activateCellSize.setForeground(Color.WHITE);
        activateCellSize.setFocusPainted(false);
        
        sizePanel.add(cellSizePrompt);
        sizePanel.add(cellSizeDropDown);
        sizePanel.add(squareBoard);
        sizePanel.add(activateCellSize);
        sizePanel.setBackground(Color.BLACK);
    }

    private void makePatternDropDown()
    {
        patternChoices = new String[5];
        patternChoices[0] = "Glider";
        patternChoices[1] = "Oscillator";
        patternChoices[2] = "BEN";
        patternChoices[3] = "Outer Ring";
        patternChoices[4] = "Abby's Flower -- set \"Square Board\"";
        
        patternDropDown = new JComboBox(patternChoices);
        patternDropDown.setSize(100, 50);
        patternDropDown.setLocation(60, 30);
        patternDropDown.setBackground(Color.DARK_GRAY);
        patternDropDown.setForeground(Color.WHITE);
        patternDropDown.setSelectedIndex(2);
    }

    private void makePatternPanel()
    {
        patternSetButtons = new JPanel(new GridLayout(1, 2));
        patternPanel = new JPanel(new GridLayout(2, 1));
        patternPanel.setPreferredSize(new Dimension(100, 100));
        makePatternDropDown();
        placePattern = new JButton("Place Pattern");
        placePattern.addActionListener(new Pattern_Listener());
        placePattern.setBackground(Color.DARK_GRAY);
        placePattern.setForeground(Color.WHITE);
        placePattern.setFocusPainted(false);
        
        clearGrid = new JButton("Clear Board");
        clearGrid.setBackground(Color.DARK_GRAY);
        clearGrid.setForeground(Color.WHITE);
        clearGrid.setFocusPainted(false);
        clearGrid.addActionListener(new ClearGrid_Listener());

        patternSetButtons.add(placePattern);
        patternSetButtons.add(clearGrid);
        patternPanel.add(patternDropDown);
        patternPanel.add(patternSetButtons);
        patternPanel.setBackground(Color.BLACK);
    }

    private void makeTimerPanel()
    {
        timerPanel = new JPanel(new BorderLayout());
        timerPanel.setPreferredSize(new Dimension(200, 100));
        
        slowTime = new JRadioButton("Relaxed");
        medTime = new JRadioButton("Fast");
        fastTime = new JRadioButton("Way Fast");
        
        timerButtonsPanel = new JPanel();
        timerButtonsPanel.setOpaque(false);
        timerButtonsPanel.setLayout(new BoxLayout(timerButtonsPanel, BoxLayout.Y_AXIS));
        
        timerRadioGroup = new ButtonGroup();
        timerRadioGroup.add(slowTime);
        timerRadioGroup.add(medTime);
        timerRadioGroup.add(fastTime);
        
        slowTime.setBackground(Color.BLACK);
        slowTime.setForeground(Color.WHITE);

        medTime.setBackground(Color.BLACK);
        medTime.setForeground(Color.WHITE);

        fastTime.setBackground(Color.BLACK);
        fastTime.setForeground(Color.WHITE);
        
        Speed_Listener spl = new Speed_Listener();
        
        slowTime.addFocusListener(spl);
        medTime.addFocusListener(spl);
        fastTime.addFocusListener(spl);
        
        timerButtonsPanel.add(slowTime);
        timerButtonsPanel.add(medTime);
        timerButtonsPanel.add(fastTime);
        
        slowTime.setSelected(true);
        
        timerButtonsPanel.setPreferredSize(new Dimension(90, 100));
        timerPanel.add(timerButtonsPanel, BorderLayout.WEST);
        
        genButtonsPanel = new JPanel(new GridLayout(2, 1, 5, 7));
        genButtonsPanel.setOpaque(false);
        
        startStop = new JButton("Start");
        startStop.setBackground(Color.DARK_GRAY);
        startStop.setForeground(Color.WHITE);
        startStop.setPreferredSize(new Dimension(70, 50));
        startStop.setFocusPainted(false);
        startStop.addActionListener(new Start_Stop_Timer());
        
        oneGeneration = new JButton("One Generation");
        oneGeneration.setBackground(Color.DARK_GRAY);
        oneGeneration.setForeground(Color.WHITE);
        oneGeneration.setFocusPainted(false);
        oneGeneration.setPreferredSize(new Dimension(50, 50));
        oneGeneration.addActionListener(new NextGen_Listener());
        oneGeneration.setFocusPainted(false);
        
        genCounterString = "Number of Generations: ";
        
        genCounterTextArea2 = new JTextArea("" + genCounter);
        genCounterTextArea = new JTextArea(genCounterString);
        genCounterTextArea2.setMargin(new Insets(0, 56, 80, 20));
        genCounterTextPanel = new JPanel(new GridLayout(2, 1));
        genCounterTextArea.setOpaque(false);
        genCounterTextArea.setForeground(Color.WHITE);
        genCounterTextArea2.setOpaque(false);
        genCounterTextArea2.setForeground(Color.WHITE);
        genCounterTextArea2.setFont(myFont);

        genCounterTextPanel.setBackground(Color.BLACK);
        genCounterTextPanel.add(genCounterTextArea);
        genCounterTextPanel.add(genCounterTextArea2);
        genButtonsPanel.add(startStop);
        genButtonsPanel.add(oneGeneration);

        timerPanel.add(genButtonsPanel, BorderLayout.CENTER);
        timerPanel.setBackground(Color.BLACK);
    }

    private void makeColorControlsPanel()
    {
        colorRadioPanel = new JPanel();
        colorRadioPanel.setOpaque(false);

        colorRadioPanel.setLayout(new BoxLayout(colorRadioPanel, BoxLayout.Y_AXIS));
        colorControlsPanel = new JPanel(new BorderLayout());

        colorControlsPanel.setPreferredSize(new Dimension(50, 100));
        colorControlsPanel.setBackground(Color.BLACK);

        colorMix = new JRadioButton("Color Mix");
        colorMix.setName("Mixup");
        colorMix.addActionListener(new ColorMix_Listener());


        colorCycle = new JRadioButton("Color Cycle");
        colorNeither = new JRadioButton("Neither");
        colorNeither.setSelected(true);

        colorMix.setBackground(Color.BLACK);
        colorMix.setForeground(Color.WHITE);

        colorCycle.setBackground(Color.BLACK);
        colorCycle.setForeground(Color.WHITE);

        colorNeither.setBackground(Color.BLACK);
        colorNeither.setForeground(Color.WHITE);

        colorRadioGroup = new ButtonGroup();
        colorRadioGroup.add(colorMix);
        colorRadioGroup.add(colorCycle);
        colorRadioGroup.add(colorNeither);
        colorRadioPanel.add(colorMix);
        colorRadioPanel.add(colorCycle);
        colorRadioPanel.add(colorNeither);

        colorControlsPanel.add(genCounterTextPanel, BorderLayout.CENTER);

        colorControlsPanel.add(colorRadioPanel, BorderLayout.EAST);
    }

    private void makeColorChooserPanel()
    {
        colorChooserPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        colorChooserPanel.setPreferredSize(new Dimension(300, 100));
        colorChooserPanel.setBackground(Color.BLACK);
        for (int i = 0; i < 8; i++)
        {
            colorChooserPanel.add(colorButton[i]);
        }

    }

    protected class GridPanel extends JPanel
    {

        int cellSize;
        int gridX = getX();//might not need these
        int gridY = getY();

        GridPanel(int cellSize)
        {
            this.cellSize = cellSize;
        }
    }

    private Boolean[][] setGridStateInfo()
    {
        Boolean[][] info = new Boolean[numberRows][numberCols];
        for (int i = 0; i < numberRows; i++)
        {
            for (int j = 0; j < numberCols; j++)
            {
                info[i][j] = (gridSquare_Button[i][j].getActionCommand().charAt(0) == popd.charAt(0)) ? true : false;
            }

        }
        return info;
    }

    private void setButtonPopdOrEmpty(String pOE, JButton jb)
    {
        jb.setBackground((pOE.equalsIgnoreCase(popd)) ? chosenColor : Color.DARK_GRAY);
        jb.setActionCommand(pOE);
    }

    private void setButtonPopdOrEmpty(String pOE, JButton jb, Color choice)
    {
        jb.setBackground((pOE.equalsIgnoreCase(popd)) ? choice : Color.DARK_GRAY);
        jb.setActionCommand(pOE);
    }

    private void updateGraphicGrid()
    {

        gridStateInfo = gol.getGridStateInfo();
        genCounter++;
        genCounterTextArea2.setText("" + genCounter);

        if (colorNeither.isSelected())
        {
            //eh?  tired.
        }

        if (colorCycle.isSelected())
        {
            switch (chosenColor.toString())
            {
                case ("java.awt.Color[r=255,g=0,b=181]"):
                    chosenColor = myBlue;
                    break;
                case ("java.awt.Color[r=25,g=0,b=255]"):
                    chosenColor = myGreen;
                    break;
                case ("java.awt.Color[r=0,g=255,b=31]"):
                    chosenColor = myOrange;
                    break;
                case ("java.awt.Color[r=232,g=114,b=0]"):
                    chosenColor = myYellow;
                    break;
                case ("java.awt.Color[r=238,g=255,b=0]"):
                    chosenColor = myPurple;
                    break;
                case ("java.awt.Color[r=188,g=107,b=242]"):
                    chosenColor = myRed;
                    break;
                case ("java.awt.Color[r=232,g=19,b=0]"):
                    chosenColor = myLightBlue;
                    break;
                case ("java.awt.Color[r=0,g=187,b=255]"):
                    chosenColor = myPink;
                    break;
                default:
                    chosenColor = myPink;

            }

        }



        for (int i = 0; i < numberRows; i++)
        {
            for (int j = 0; j < numberCols; j++)
            {

                if (colorMix.isSelected())
                {
                    Color randomChoice;
                    switch ((int) (8 * Math.random() + 1))
                    {
                        case 1:
                            randomChoice = myBlue;
                            break;
                        case 2:
                            randomChoice = myGreen;
                            break;
                        case 3:
                            randomChoice = myLightBlue;
                            break;
                        case 4:
                            randomChoice = myOrange;
                            break;
                        case 5:
                            randomChoice = myPink;
                            break;
                        case 6:
                            randomChoice = myPurple;
                            break;
                        case 7:
                            randomChoice = myRed;
                            break;
                        case 8:
                            randomChoice = myYellow;
                            break;
                        default:
                            randomChoice = Color.BLACK;

                    }

                    setButtonPopdOrEmpty((gridStateInfo[i][j]) ? popd : empty, gridSquare_Button[i][j], randomChoice);
                } else
                {
                    setButtonPopdOrEmpty((gridStateInfo[i][j]) ? popd : empty, gridSquare_Button[i][j]);
                }
            }
        }

    }

    private class Start_Stop_Timer implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (slowTime.isSelected())
            {
                nextGenTimer.setDelay(400);
            } else if (medTime.isSelected())
            {
                nextGenTimer.setDelay(100);
            } else if (fastTime.isSelected())
            {
                nextGenTimer.setDelay(40);
            }


            if (((JButton) ae.getSource()).getText().equalsIgnoreCase("Start"))
            {
                nextGenTimer.start();
                ((JButton) ae.getSource()).setText("Stop");
            } else
            {
                nextGenTimer.stop();
                ((JButton) ae.getSource()).setText("Start");
            }
        }
    }

    private class Speed_Listener implements FocusListener
    {

        @Override
        public void focusGained(FocusEvent fe)
        {
            if (((JRadioButton) fe.getSource()).equals(slowTime))
            {
                nextGenTimer.setDelay(400);
            } else if (((JRadioButton) fe.getSource()).equals(medTime))
            {
                nextGenTimer.setDelay(100);
            } else if (((JRadioButton) fe.getSource()).equals(fastTime))
            {
                nextGenTimer.setDelay(40);
            }

        }

        @Override
        public void focusLost(FocusEvent fe)
        {
        }
    }

    private class NextGen_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            gol.setGridInfo_FromExternalInput(setGridStateInfo());
            gol.nextGeneration();
            updateGraphicGrid();
        }
    }

    private class ColorMix_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (((JRadioButton) ae.getSource()).getActionCommand().equalsIgnoreCase("Cycle"))
            {
                colorMixup = false;
                if (!(cycleColors))
                {
                    cycleColors = true;
                } else
                {
                    cycleColors = false;
                }
            } else
            {

                if (!(colorMixup))
                {
                    colorMixup = true;
                } else
                {
                    colorMixup = false;
                }
            }
        }
    }

    private class ClearGrid_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            gol.clearGrid();
            updateGraphicGrid();
            genCounter = 0;
            genCounterTextArea.setText(genCounterString);
            genCounterTextArea2.setText("" + genCounter);
        }
    }

    private class Pattern_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            genCounter = -1;

            switch (patternChoices[patternDropDown.getSelectedIndex()])
            {
                case "Glider":
                    gol.clearGrid();                     
                    gol.makeGlider();
                    updateGraphicGrid();

                    break;
                case "Oscillator":
                    gol.clearGrid();
                    gol.makeOscillator();
                    updateGraphicGrid();

                    break;
                case "BEN":
                    gol.clearGrid();
                    gol.makeBEN();
                    updateGraphicGrid();

                    break;
                case "Outer Ring":
                    gol.clearGrid();
                    gol.makeOuterRing();
                    updateGraphicGrid();
                    break;
                case "Abby's Flower -- set \"Square Board\"":
                    gol.clearGrid();                    
                    gol.makeAbby_sFlower();
                    updateGraphicGrid();
                    break;
            }

        }
    }

    private class Mouse_Activity_Listener implements MouseListener, KeyListener
    {

        @Override
        public void keyTyped(KeyEvent ke)
        {
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
        }

        @Override
        public void keyReleased(KeyEvent ke)
        {
            if (ke.getKeyCode() == KeyEvent.VK_SHIFT)
            {
                shift = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_ALT)
            {
                alt = false;
            }

        }

        @Override
        public void mouseClicked(MouseEvent me)
        {
        }

        @Override
        public void mousePressed(MouseEvent me)
        {
        }

        @Override
        public void mouseReleased(MouseEvent me)
        {
        }

        @Override
        public void mouseEntered(MouseEvent me)
        {
            if (me.isControlDown() || (me.isControlDown() && me.isShiftDown()))
            {
                setButtonPopdOrEmpty(empty, (JButton) me.getSource());
            }
            if (me.isShiftDown() && !(me.isControlDown()))
            {
                setButtonPopdOrEmpty(popd, (JButton) me.getSource());
            }
        }

        @Override
        public void mouseExited(MouseEvent me)
        {
        }
    }

    private class Instructions_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (iNeed.getText().equalsIgnoreCase("Instructions, please..."))
            {
                iNeed.setText("Hide Instructions");
                instructions1.setVisible(true);
            }else if (iNeed.getText().equalsIgnoreCase("Hide Instructions"))
            {
                iNeed.setText("Instructions, please...");
                instructions1.setVisible(false);
            }
            
        }
        
    }
    private class Timer_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            gol.setGridInfo_FromExternalInput(setGridStateInfo());
            gol.nextGeneration();
            updateGraphicGrid();


        }
    }

    private class GridSquare_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (((JButton) ae.getSource()).getActionCommand().equalsIgnoreCase(popd))
            {
                setButtonPopdOrEmpty(empty, (JButton) ae.getSource());
                System.out.println((int) ((JButton) ae.getSource()).getActionCommand().charAt(0));


            } else
            {
                setButtonPopdOrEmpty(popd, (JButton) ae.getSource());
                System.out.println((int) ((JButton) ae.getSource()).getActionCommand().charAt(0));
            }
        }
    }

    private class Color_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            chosenColor = ((JButton) ae.getSource()).getBackground();


            ((JButton) ae.getSource()).setBorderPainted(true);

            for (int i = 0; i < colorButton.length; i++)
            {
                if (!(colorButton[i].equals(((JButton) ae.getSource()))))
                {
                    colorButton[i].setBorderPainted(false);
                }
            }
        }
    }

    private class Size_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {

            removeGridButtons();

            cellSize = Integer.parseInt((String) (cellSizeDropDown.getSelectedItem()));

            
            
            numberRows = gridWidth / cellSize;
            
            if (squareBoard.isSelected())
            {
            
            if (numberRows % 2 != 0)
            {
                numberRows -= 1;
            }

            numberCols = numberRows;
            }else
            {
                numberCols = gridHeight/cellSize;
            }
            gridPanel.setSize(cellSize * numberCols, 720);//where is 720 coming from??
            gol = new Game_Of_Life(numberRows, numberCols);
            
            initializeGridButtons();
            addGridButtons();
            genCounter = 0;
            genCounterTextArea.setText(genCounterString);
            genCounterTextArea2.setText("" + genCounter);


        }
    }

    public static void main(String[] args)
    {

        JFrame golFrame = new JFrame();

        golFrame.setSize(1200, 820);
        golFrame.setLocation(0, 0);
        golFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graphic_Game_Of_Life bgol = new Graphic_Game_Of_Life(10, golFrame.getHeight() - 190, golFrame.getWidth() - 16);
        golFrame.add(bgol);
        golFrame.setVisible(true);
    }
}
