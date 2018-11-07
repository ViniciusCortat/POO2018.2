package Ludo;

import java.util.ArrayList;

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
			Matrix[8][13] = 2;
			Matrix[1][8] = 3;
			Matrix[13][6] = 4;
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
