package Ludo;

public class Casas 
{
	//ArrayList<ArrayList<Integer>> MatrizCasas;// = new ArrayList<ArrayList<Integer>>();
	private Integer[][] _matrizCasas;
	private Integer _dimension;
	private Integer _qtdCasas;
	
	public Casas(Integer qtdCasas)
	{
		_qtdCasas = qtdCasas;
		_dimension = (int)Math.sqrt(qtdCasas);
		_matrizCasas = new Integer[_dimension][_dimension];//new ArrayList<ArrayList<Integer>>(qtdCasas);		
		FillMatrixZero(_matrizCasas,_dimension);	
	}
	
	private void FillMatrixZero(Integer[][] Matrix,int Dimension) // Needs to be a SquareMatrix
	{
		for(int i =0;i < Dimension;i++) // Reset the hole matrix
		{
			for(int j = 0;j < Dimension;j++)
			{
				Matrix[i][j] = -1;
			}
		}
		for(int i = 0;i < 3;i++) // Draw black squares
		{
			for(int j = 0;j < Dimension;j++)
			{
				Matrix[6+i][j] = 0;
				Matrix[j][6+i] = 0;
			}
		}
		for(int i  = 0; i < 5; i++) // Draw colored squares
		{
			Matrix[7][i+1] = 1;
			Matrix[7][i+9] = 2;

			Matrix[i+1][7] = 3;
			Matrix[i+9][7] = 4;
		}
			Matrix[6][1] = 1;
			Matrix[8][1] = 5; // BLACK SQUARE
			
			Matrix[8][13] = 2;
			Matrix[6][13] = 5;
			
			Matrix[1][8] = 3;
			Matrix[1][6] = 5;
			
			Matrix[13][6] = 4;
			Matrix[13][8] = 5;
			
			Matrix[1][1] = 6; // WHITE CIRCLE
			Matrix[1][4] = 6;
			
			Matrix[4][1] = 6;
			Matrix[4][4] = 6;
			
			Matrix[1][10] = 6;
			Matrix[4][10] = 6;
			
			Matrix[4][13] = 6;
			Matrix[1][13] = 6;
			
			Matrix[13][4] = 6;
			Matrix[13][1] = 6;
			
			Matrix[10][1] = 6;
			Matrix[10][4] = 6;
			
			Matrix[10][13] = 6;
			Matrix[13][10] = 6;
			
			Matrix[10][10] = 6;
			Matrix[13][13] = 6;
			
			for(int i =0;i < Dimension;i++) // Reset the hole matrix
			{
				for(int j = 0;j < Dimension;j++)
				{
					if((i == 6 || i == 8 || i == 0 || i == 14) && (j == 0 || j == 14 || j == 6 || j == 8))
					{
						Matrix[i][j] = -2;
					}
					else if((i == 0 || i == 7 || i == 14) && (j == 0 || j == 7 || j == 14))
					{
						Matrix[i][j] = -4;
					}
				}
			}
			
			Matrix[6][6] = -3;
			Matrix[6][8] = -3;
			
			Matrix[8][6] = -3;
			Matrix[8][8] = -3;			
	}
	
	public Integer GetDimension()
	{
		return _dimension;
	}
	public Integer GetCount()
	{
		return _qtdCasas;
	}
	public Integer GetMatrixValue(int i,int j)
	{
		return _matrizCasas[i][j];
	}	
	public String ToString()
    {
        String MatrixOut = String.format("Matrix {0}X{1}\n   ", _dimension, _dimension);
        
        for(int i = 0; i < _dimension; i++)
        {
            MatrixOut += i + " ";            
        }
        MatrixOut += "\n";
        
        for (int i = 0; i < _dimension; i++)
        {            
            MatrixOut += i + " ";
            for (int j = 0; j < _dimension; j++)
            {                            
                MatrixOut += _matrizCasas[i][j]+ " ";                 
            }
            MatrixOut += "\n";            
        }
        return MatrixOut;
    }


}