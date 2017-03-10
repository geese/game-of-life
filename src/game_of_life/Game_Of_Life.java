package game_of_life;

public class Game_Of_Life
{
    private int numRows;
    private int numCols;
    private char popd = '%';
    private char empty = '`';
    private char[][] grid;

    public Game_Of_Life(int rows, int cols)
    {
        numRows = rows;
        numCols = cols;
        grid = new char[numRows][numCols];
        
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = empty;
            }
        }

    }
    
//   public void setRows(int rows)
//   {
//       numRows = rows;
//   }
//
//   public void setCols(int cols)
//   {
//       numCols = cols;
//   } 
   
    public void clearGrid()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = empty;
            }
        }

    }

//    public void makeSkyscraperAndMoon()
//    {
//        grid[2][7] = grid[2][8] = popd;
//        
//    }
    
    public void makeGlider()
    {
        grid[2][8] = grid[2][9] = grid[2][10] = grid[1][10] = grid[0][9] = popd;
    }

    public void makeAbby_sFlower()
    {
        //int j = numCols;
        for (int i = numRows; i > 0; i--)
        {
            grid[numRows - i][numCols - i] = popd;
            grid[(numRows - 1) - (numRows - i)][numCols - i] = popd;
        }
    }
    
    public void makeOscillator()
    {
        grid[3][3] = grid[3][4] = grid[3][5] = popd;
    }

    public void makeOuterRing()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = popd;
            }

        }
        for (int i = numRows - 2; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = popd;
            }
        }

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                grid[i][j] = popd;
            }

        }
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = numCols - 2; j < grid[i].length; j++)
            {
                grid[i][j] = popd;
            }

        }

    }

    public void makeOscillatorSoup()
    {
        for (int i = 0; i < grid.length; i++)
        {
            int surprise = (int) (36 * Math.random() + 1);
            grid[i][surprise] = grid[i][surprise + 1] = grid[i][surprise + 2] = popd;
        }
    }

    public void makeBEN() // (ok, this is nerdy)
    {
        grid[4][5] = grid[4][6] = grid[4][7] = grid[4][8] = grid[4][9] = grid[4][10] = grid[4][11] = grid[4][12] =
                grid[4][17] = grid[4][18] = grid[4][19] = grid[4][20] = grid[4][21] = grid[4][22] = grid[4][23] = grid[4][24] =
                grid[4][27] = grid[4][28] = grid[4][29] =
                grid[4][38] = grid[4][39] = popd;
        grid[5][5] = grid[5][6] = grid[5][7] = grid[5][8] = grid[5][9] = grid[5][10] = grid[5][11] = grid[5][12] = grid[5][13] = grid[5][14] =
                grid[5][17] = grid[5][18] = grid[5][19] = grid[5][20] = grid[5][21] = grid[5][22] = grid[5][23] = grid[5][24] =
                grid[5][27] = grid[5][28] = grid[5][29] =
                grid[5][38] = grid[5][39] = popd;
        grid[6][5] = grid[6][6] = grid[6][13] = grid[6][14] =
                grid[6][17] = grid[6][18] =
                grid[6][27] = grid[6][28] = grid[6][29] = grid[6][30] =
                grid[6][38] = grid[6][39] = popd;
        grid[7][5] = grid[7][6] = grid[7][13] = grid[7][14] =
                grid[7][17] = grid[7][18] =
                grid[7][27] = grid[7][28] = grid[7][29] = grid[7][30] =
                grid[7][38] = grid[7][39] = popd;
        grid[8][5] = grid[8][6] = grid[8][13] = grid[8][14] =
                grid[8][17] = grid[8][18] =
                grid[8][27] = grid[8][28] = grid[8][29] = grid[8][30] = grid[8][31] =
                grid[8][38] = grid[8][39] = popd;
        grid[9][5] = grid[9][6] = grid[9][13] = grid[9][14] =
                grid[9][17] = grid[9][18] =
                grid[9][27] = grid[9][28] = grid[9][30] = grid[9][31] =
                grid[9][38] = grid[9][39] = popd;
        grid[10][5] = grid[10][6] = grid[10][13] = grid[10][14] =
                grid[10][17] = grid[10][18] =
                grid[10][27] = grid[10][28] = grid[10][30] = grid[10][31] = grid[10][32] =
                grid[10][38] = grid[10][39] = popd;
        grid[11][5] = grid[11][6] = grid[11][12] = grid[11][13] = grid[11][14] =
                grid[11][17] = grid[11][18] =
                grid[11][27] = grid[11][28] = grid[11][31] = grid[11][32] =
                grid[11][38] = grid[11][39] = popd;
        grid[12][5] = grid[12][6] = grid[12][10] = grid[12][11] = grid[12][12] = grid[12][13] =
                grid[12][17] = grid[12][18] =
                grid[12][27] = grid[12][28] = grid[12][31] = grid[12][32] = grid[12][33] =
                grid[12][38] = grid[12][39] = popd;
        grid[13][5] = grid[13][6] = grid[13][10] = grid[13][11] = grid[13][12] =
                grid[13][17] = grid[13][18] = grid[13][19] = grid[13][20] = grid[13][21] = grid[13][22] =
                grid[13][27] = grid[13][28] = grid[13][32] = grid[13][33] =
                grid[13][38] = grid[13][39] = popd;
        grid[14][5] = grid[14][6] = grid[14][10] = grid[14][11] = grid[14][12] = grid[14][13] = grid[14][14] =
                grid[14][17] = grid[14][18] = grid[14][19] = grid[14][20] = grid[14][21] = grid[14][22] =
                grid[14][27] = grid[14][28] = grid[14][32] = grid[14][33] = grid[14][34] =
                grid[14][38] = grid[14][39] = popd;
        grid[15][5] = grid[15][6] = grid[15][12] = grid[15][13] = grid[15][14] = grid[15][15] =
                grid[15][17] = grid[15][18] =
                grid[15][27] = grid[15][28] = grid[15][33] = grid[15][34] =
                grid[15][38] = grid[15][39] = popd;
        grid[16][5] = grid[16][6] = grid[16][14] = grid[16][15] =
                grid[16][17] = grid[16][18] =
                grid[16][27] = grid[16][28] = grid[16][33] = grid[16][34] = grid[16][35] =
                grid[16][38] = grid[16][39] = popd;
        grid[17][5] = grid[17][6] = grid[17][14] = grid[17][15] =
                grid[17][17] = grid[17][18] =
                grid[17][27] = grid[17][28] = grid[17][34] = grid[17][35] =
                grid[17][38] = grid[17][39] = popd;
        grid[18][5] = grid[18][6] = grid[18][14] = grid[18][15] =
                grid[18][17] = grid[18][18] =
                grid[18][27] = grid[18][28] = grid[18][34] = grid[18][35] = grid[18][36] =
                grid[18][38] = grid[18][39] = popd;
        grid[19][5] = grid[19][6] = grid[19][14] = grid[19][15] =
                grid[19][17] = grid[19][18] =
                grid[19][27] = grid[19][28] = grid[19][35] = grid[19][36] =
                grid[19][38] = grid[19][39] = popd;
        grid[20][5] = grid[20][6] = grid[20][14] = grid[20][15] =
                grid[20][17] = grid[20][18] =
                grid[20][27] = grid[20][28] = grid[20][35] = grid[20][36] = grid[20][37] =
                grid[20][38] = grid[20][39] = popd;
        grid[21][5] = grid[21][6] = grid[21][14] = grid[21][15] =
                grid[21][17] = grid[21][18] =
                grid[21][27] = grid[21][28] = grid[21][36] = grid[21][37] =
                grid[21][38] = grid[21][39] = popd;
        grid[22][5] = grid[22][6] = grid[22][14] = grid[22][15] =
                grid[22][17] = grid[22][18] =
                grid[22][27] = grid[22][28] = grid[22][36] = grid[22][37] =
                grid[22][38] = grid[22][39] = popd;
        grid[23][5] = grid[23][6] = grid[23][7] = grid[23][8] = grid[23][9] = grid[23][10] = grid[23][11] = grid[23][12] = grid[23][13] = grid[23][14] = grid[23][15] =
                grid[23][17] = grid[23][18] = grid[23][19] = grid[23][20] = grid[23][21] = grid[23][22] = grid[23][23] = grid[23][24] =
                grid[23][27] = grid[23][28] = grid[23][37] =
                grid[23][38] = grid[23][39] = popd;
        grid[24][5] = grid[24][6] = grid[24][7] = grid[24][8] = grid[24][9] = grid[24][10] = grid[24][11] = grid[24][12] = grid[24][13] = grid[24][14] =
                grid[24][17] = grid[24][18] = grid[24][19] = grid[24][20] = grid[24][21] = grid[24][22] = grid[24][23] = grid[24][24] =
                grid[24][27] = grid[24][28] = grid[24][37] =
                grid[24][38] = grid[24][39] = popd;





    }

    private Boolean survive(char[] cellNeighbors)
    {
        int neighborCounter = 0;

        for (int i = 0; i < cellNeighbors.length; i++)
        {
            neighborCounter += (int) cellNeighbors[i];
        }

        if (neighborCounter == 650 || neighborCounter == 591)
        {
            return true;
        } else
        {
            return false;
        }
    }

    private Boolean breed(char[] cellNeighbors)
    {
        int neighborCounter = 0;

        for (int i = 0; i < cellNeighbors.length; i++)
        {
            neighborCounter += (int) cellNeighbors[i];
        }

        if (neighborCounter == 591)
        {
            return true;
        } else
        {
            return false;
        }
    }

    protected void nextGeneration()
    {
        char[][] gridTemp = new char[numRows][numCols];

        for (int i = 0; i < gridTemp.length; i++)
        {
            System.arraycopy(grid[i], 0, gridTemp[i], 0, gridTemp[i].length);
        }

        for (int x = 0; x < gridTemp.length; x++)
        {
            for (int y = 0; y < gridTemp[x].length; y++)
            {
                if (grid[x][y] == popd)
                {
                    if (!(survive(loadNeighborArray(x, y))))
                    {
                        gridTemp[x][y] = empty;
                    }

                } else if (grid[x][y] == empty)
                {
                    Boolean breed = breed(loadNeighborArray(x, y));
                    if (breed(loadNeighborArray(x, y)))
                    {
                        gridTemp[x][y] = popd;
                    }
                }
            }
        }




        for (int i = 0; i < gridTemp.length; i++)
        {
            System.arraycopy(gridTemp[i], 0, grid[i], 0, grid[i].length);
        }

    }

    public Boolean[][] getGridStateInfo() //i, j < numRows
    {
        Boolean[][] isPopd = new Boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                isPopd[i][j] = (grid[i][j] == popd) ? true : false;
            }
        }

        return isPopd;
    }

    public void setGridInfo_FromExternalInput(Boolean[][] info)
    {
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                grid[i][j] = ( info[i][j]) ? popd : empty;
                
            }
        }
    }
    

    public char[][] getNextGenSetup()
    {
        char[][] nextGenSetup = new char[numRows][numCols];

        for (int i = 0; i < grid.length; i++)
        {
            System.arraycopy(grid[i], 0, nextGenSetup[i], 0, nextGenSetup[i].length);
        }

        return nextGenSetup;
    }

    private char[] loadNeighborArray(int x, int y)
    {
        char[] neighborArray = new char[8];

        if (x == 0 || y == 0)
        {
            if (x == 0)
            {
                if (y == 0)
                {
                    neighborArray[0] = grid[numRows-1][numCols-1];
                    neighborArray[1] = grid[numRows-1][y];
                    neighborArray[2] = grid[numRows-1][y + 1];
                    neighborArray[3] = grid[x][numCols-1];
                    neighborArray[4] = grid[x][y + 1];
                    neighborArray[5] = grid[x + 1][numCols-1];
                    neighborArray[6] = grid[x + 1][y];
                    neighborArray[7] = grid[x + 1][y + 1];

                } else if (y == numCols-1)
                {
                    neighborArray[0] = grid[numRows-1][y - 1];
                    neighborArray[1] = grid[numRows-1][y];
                    neighborArray[2] = grid[numRows-1][0];
                    neighborArray[3] = grid[x][y - 1];
                    neighborArray[4] = grid[x][0];
                    neighborArray[5] = grid[x + 1][y - 1];
                    neighborArray[6] = grid[x + 1][y];
                    neighborArray[7] = grid[x + 1][0];

                } else
                {
                    neighborArray[0] = grid[numRows-1][y - 1];
                    neighborArray[1] = grid[numRows-1][y];
                    neighborArray[2] = grid[numRows-1][y + 1];
                    neighborArray[3] = grid[x][y - 1];
                    neighborArray[4] = grid[x][y + 1];
                    neighborArray[5] = grid[x + 1][y - 1];
                    neighborArray[6] = grid[x + 1][y];
                    neighborArray[7] = grid[x + 1][y + 1];
                }
            } else if (x == numRows-1)
            {
                neighborArray[0] = grid[x - 1][numCols-1];
                neighborArray[1] = grid[x - 1][y];
                neighborArray[2] = grid[x - 1][y + 1];
                neighborArray[3] = grid[x][numCols-1];
                neighborArray[4] = grid[x][y + 1];
                neighborArray[5] = grid[0][numCols-1];
                neighborArray[6] = grid[0][y];
                neighborArray[7] = grid[0][y + 1];
            } else
            {
                neighborArray[0] = grid[x - 1][numCols-1];
                neighborArray[1] = grid[x - 1][y];
                neighborArray[2] = grid[x - 1][y + 1];
                neighborArray[3] = grid[x][numCols-1];
                neighborArray[4] = grid[x][y + 1];
                neighborArray[5] = grid[x + 1][y];
                neighborArray[6] = grid[x + 1][y];
                neighborArray[7] = grid[x + 1][y + 1];
            }
        } else if (x == numRows-1 || y == numCols-1)
        {
            if (x == numRows-1)
            {
                if (y == numCols-1)
                {
                    neighborArray[0] = grid[x - 1][y - 1];
                    neighborArray[1] = grid[x - 1][y];
                    neighborArray[2] = grid[x - 1][0];
                    neighborArray[3] = grid[x][y - 1];
                    neighborArray[4] = grid[x][0];
                    neighborArray[5] = grid[0][y - 1];
                    neighborArray[6] = grid[0][y];
                    neighborArray[7] = grid[0][0];

                } else
                {
                    neighborArray[0] = grid[x - 1][y - 1];
                    neighborArray[1] = grid[x - 1][y];
                    neighborArray[2] = grid[x - 1][y + 1];
                    neighborArray[3] = grid[x][y - 1];
                    neighborArray[4] = grid[x][y + 1];
                    neighborArray[5] = grid[0][y - 1];
                    neighborArray[6] = grid[0][y];
                    neighborArray[7] = grid[0][y + 1];

                }
            } else
            {
                neighborArray[0] = grid[x - 1][y - 1];
                neighborArray[1] = grid[x - 1][y];
                neighborArray[2] = grid[x - 1][0];
                neighborArray[3] = grid[x][y - 1];
                neighborArray[4] = grid[x][0];
                neighborArray[5] = grid[x + 1][y - 1];
                neighborArray[6] = grid[x + 1][y];
                neighborArray[7] = grid[x + 1][0];

            }
        } else
        {
            neighborArray[0] = grid[x - 1][y - 1];
            neighborArray[1] = grid[x - 1][y];
            neighborArray[2] = grid[x - 1][y + 1];
            neighborArray[3] = grid[x][y - 1];
            neighborArray[4] = grid[x][y + 1];
            neighborArray[5] = grid[x + 1][y - 1];
            neighborArray[6] = grid[x + 1][y];
            neighborArray[7] = grid[x + 1][y + 1];
        }

        return neighborArray;

    }

    public String toString()
    {
        String printGrid = "";

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                printGrid += grid[i][j];
                if (j == numCols - 1)
                {
                    printGrid += "\n";
                }
            }
            if (i == numRows - 1)
            {
                printGrid += "\n";
            }
        }

        return printGrid;
    }

    public static void main(String[] args)
    {
        Game_Of_Life gol = new Game_Of_Life(30, 40);

        gol.makeOscillator();
        System.out.println(gol.toString());

        for (int x = 0; x < 10; x++)
        {
            gol.nextGeneration();
            System.out.println(gol.toString());
        }

        gol = new Game_Of_Life(20,11);
                
        gol.makeGlider();
        System.out.println(gol.toString());
        
      
        gol.makeAbby_sFlower();
        System.out.println(gol.toString());
        
        

//        for (int x = 0; x < 10; x++)
//        {
//            gol.nextGeneration();
//            System.out.println(gol.toString());
//        }
//
//        gol.clearGrid();
//
//        gol.makeOuterRing();
//        System.out.println(gol.toString());
//
//        for (int x = 0; x < 10; x++)
//        {
//            gol.nextGeneration();
//            System.out.println(gol.toString());
//        }
//
//        gol.clearGrid();
//
//        gol.makeOscillatorSoup();
//        System.out.println(gol.toString());
//
//        for (int x = 0; x < 10; x++)
//        {
//            gol.nextGeneration();
//            System.out.println(gol.toString());
//        }
//        gol.clearGrid();
//
//        gol.makeBEN();
//        System.out.println(gol.toString());
//
//        for (int x = 0; x < 10; x++)
//        {
//            gol.nextGeneration();
//            System.out.println(gol.toString());
//        }

    }
}