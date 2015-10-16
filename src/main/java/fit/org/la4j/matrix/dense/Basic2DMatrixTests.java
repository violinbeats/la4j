package fit.org.la4j.matrix.dense;

import java.util.Random;

import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.Matrix;
import fit.ColumnFixture;

public class Basic2DMatrixTests extends ColumnFixture{

	private final double ZERO = 0.0;
	private final double ONE = 1.0;
	private int row1, col1;//test matrix dimension
	private int mar, mac, mbr, mbc, mcr, mcc, mdr, mdc; //block test
	private double eps = 1e-5;//double equality epsilon
	private Random random = new Random();
	private double[] firstRow = new double[]{3, 5, 7};
	private double[] secondRow = new double[]{1, 9, 2};
	private Basic2DMatrix a = new Basic2DMatrix(new double[][]{firstRow, secondRow});
	

    public boolean zeroBasic2DMatrixTest() {
    	Basic2DMatrix zero = Basic2DMatrix.zero(row1, col1);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < col1; c++) {
    			if (!assertEquals(zero.get(r, c), ZERO, eps)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
 
    public boolean constantBasic2DMatrixTest() {
    	final double constant = 10.0;
    	Basic2DMatrix constantM = Basic2DMatrix.constant(row1, col1, constant);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < col1; c++) {
    			if (!assertEquals(constantM.get(r, c), constant, eps)) return false;
    		}
    	}
    	return true;
    }

    
    public boolean diagnolBasic2DMatrixTest() {
    	final double diagnolNumber = 10.0;
    	Basic2DMatrix diagnolM = Basic2DMatrix.diagonal(row1, diagnolNumber);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < row1; c++) {
    			if(r == c)
    				if (!assertEquals(diagnolM.get(r, c), diagnolNumber, eps)) return false;
    			else
    				if (!assertEquals(diagnolM.get(r, c), ZERO, eps)) return false;
    		}
    	}
    	return true;
    }
    
    
    public boolean unitBasic2DMatrixTest() {
    	Basic2DMatrix unitM = Basic2DMatrix.unit(row1, col1);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < row1; c++) {
    			if (!assertEquals(unitM.get(r, c), ONE, eps)) return false;
    		}
    	}
    	return true;
    }


    public boolean identityBasic2DMatrixTest() {
    	Basic2DMatrix identityM = Basic2DMatrix.identity(row1);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < row1; c++) {
    			if(r == c) {
    				if (!assertEquals(identityM.get(r, c), ONE, eps)) return false;
    			}
    				
    			else {
    				if (!assertEquals(identityM.get(r, c), ZERO, eps)) return false;
    			}
    		}
    	}
    	return true;
    }


    public boolean randomBasic2DMatrixTest() {
    	Random random = new Random();
    	Basic2DMatrix randomM = Basic2DMatrix.random(row1, col1, random);
    	Basic2DMatrix randomM2 = Basic2DMatrix.random(row1, col1, random);
    	boolean isDifferent = false;
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < col1; c++) {
    			if(randomM.get(r, c) != randomM2.get(r, c)) {
    				isDifferent = true;
    			}
    		}
    	}
    	return isDifferent;
    }


    public boolean randomSymmentricBasic2DMatrixTest() {
    	Random random = new Random();
    	Basic2DMatrix randomSM = Basic2DMatrix.randomSymmetric(row1, random);
    	Basic2DMatrix randomSM2 = Basic2DMatrix.randomSymmetric(row1, random);
    	boolean isDifferent = false;
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < row1; c++) {
    			if(randomSM.get(r, c) != randomSM2.get(r, c)) {
    				isDifferent = true;
    			}
    		if (!assertEquals(randomSM.get(r, c), randomSM.get(c, r), eps) || !assertEquals(randomSM2.get(r, c), randomSM2.get(c, r), eps)) {
    			return false;
    		}
    		}
    	}
    	return isDifferent;
    }
    

    public boolean from1DArrayTest() {
    	double[] array = new double[]{4,2,7,5,3,7};
    	Basic2DMatrix matrix = Basic2DMatrix.from1DArray(row1, col1, array);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < col1; c++) {
    			if (!assertEquals(matrix.get(r, c), array[r * col1 + c], eps)) return false;
    		}
    	}
    	return true;
    }


//    @Test(expected = ArrayIndexOutOfBoundsException.class)
//    public void from1DArrayMustNotBeSmallerThanNeeded() {
//    	double[] array = new double[]{4,2,7,5};
//    	Basic2DMatrix.from1DArray(row1, col1, array);
//    }
//    

    public boolean from2DArrayTest() {
    	double[][] array = new double[][]
    			{firstRow, secondRow};
    	Basic2DMatrix matrix = Basic2DMatrix.from2DArray(array);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < col1; c++) {
    			if (!assertEquals(matrix.get(r, c), array[r][c], eps)) return false;
    		}
    	}
    	return true;
    }
    

    public boolean blockTest() {
    	Basic2DMatrix ma = Basic2DMatrix.random(mar, mac, random);
    	Basic2DMatrix mb = Basic2DMatrix.random(mbr, mbc, random);
    	Basic2DMatrix mc = Basic2DMatrix.random(mcr, mcc, random);
    	Basic2DMatrix md = Basic2DMatrix.random(mdr, mdc, random);

    	Basic2DMatrix matrix = Basic2DMatrix.block(ma, mb, mc, md);
    	Basic2DMatrix subMatrix;
    	System.out.println(mb.get(0, 0));
    	System.out.println(mb.get(1, 0));
    	
    	//ma
    	subMatrix = getSubMatrix(matrix, 0, 0, mar, mac);
    	if (!assertMatrixEquals(subMatrix, ma, eps)) {
    		return false;
    	};
    	//mb
    	subMatrix = getSubMatrix(matrix, 0, mac, mbr, mbc);
    	if (!assertMatrixEquals(subMatrix, mb, eps)) {
    		return false;
    	};
    	//mc
    	subMatrix = getSubMatrix(matrix, mar, 0, mcr, mcc);
    	if (!assertMatrixEquals(subMatrix, mc, eps)) {
    		return false;
    	};
    	//md
    	subMatrix = getSubMatrix(matrix, mar, mac, mdr, mdc);
    	if (!assertMatrixEquals(subMatrix, md, eps)) {
    		return false;
    	};
    	return true;
    }



    public boolean getTest() {
    	//first element of matrix a is 3.0
    	if (!assertEquals(a.get(0, 0), 3.0, eps)) return false;
    	return true;
    }

//    @Test(expected = ArrayIndexOutOfBoundsException.class)
//    public void getOutOfBoundsTest() {
//    	a.get(100, 100);
//    }
//    

    public boolean setTest() {
    	double newValue = 10;
    	a.set(0, 0, newValue);
    	if (!assertEquals(a.get(0, 0), newValue, eps)) return false;
    	return true;
    }

//    @Test(expected = ArrayIndexOutOfBoundsException.class)
//    public void setOutOfBoundsTest() {
//    	a.set(100, 100, 0);
//    }
//    

    public boolean setAllTest() {
    	double newValue = 10;
    	a.setAll(newValue);
    	for(int r = 0; r < row1; r++) {
    		for(int c = 0; c < col1; c++) {
    			if (!assertEquals(a.get(r, c), newValue, eps)) return false;
    		}
    	}
    	return true;
    }
    

    public boolean swapRowTest() {
    	a.swapRows(0, 1);
    	double[] newFirstRow =
    			new double[]{a.getRow(0).get(0),
    					a.getRow(0).get(1)};
    	double[] newSecondRow = 
    			new double[]{a.getRow(1).get(0),
    					a.getRow(1).get(1)};
    	if (!assertDoubleArrayEquals(newFirstRow, secondRow, eps)) return false;
    	if (!assertDoubleArrayEquals(newSecondRow, firstRow, eps)) return false;
    	return true;
    }
    

    public boolean swapColumnTest() {
    	//original: 3, 5, 7 ==> should be 5, 3, 7
    	//          1, 9, 2               9, 1, 2
    	a.swapColumns(0, 1);
    	double[] newFirstCol =
    			new double[]{a.getColumn(0).get(0), 
    					a.getColumn(0).get(1)};
    	double[] newSecondCol =
    			new double[]{a.getColumn(1).get(0),
    					a.getColumn(1).get(1)};
    	if (!assertDoubleArrayEquals(newFirstCol, new double[]{5,9}, eps)) return false;
    	if (!assertDoubleArrayEquals(newSecondCol, new double[]{3,1}, eps)) return false;
    	return true;
    }
    

    public boolean getRowTest() {
    	double[] firstRow2 = new double[]{
    			a.getRow(0).get(0),
    			a.getRow(0).get(1)};
    	if (!assertDoubleArrayEquals(firstRow2, firstRow, eps)) return false;
    	return true;
    }
    
//    @Test(expected = ArrayIndexOutOfBoundsException.class)
//    public void getRowTestIndexOutOfBound() {
//    	a.getRow(100);
//    }
    

//    public void copyOfShapeTest() {
//    	Basic2DMatrix matrix = (Basic2DMatrix) a.copyOfShape(2, 2);
//    	assertEquals(matrix.get(0, 0), 3, eps);
//    	assertEquals(matrix.get(0, 1), 5, eps);
//    	assertEquals(matrix.get(1, 0), 1, eps);
//    	assertEquals(matrix.get(1, 1), 9, eps);
//    }
//    
//    @Test
//    public void toArrayTest() {
//    	double[][] array = a.toArray();
//    	assertDoubleArrayEquals(array[0], firstRow, eps);
//    	assertDoubleArrayEquals(array[1], secondRow, eps);
//    }
//    
//    @Test
//    public void blankOfShapeTest() {
//    	Basic2DMatrix matrix = (Basic2DMatrix) a.blankOfShape(2, 2);
//    	Basic2DMatrix matrixShouldBe = Basic2DMatrix.zero(2, 2);
//    	assertMatrixEquals(matrix, matrixShouldBe, eps);
//    }
//    
    private boolean assertDoubleArrayEquals(double[] array1, double[] array2, double eps) {
    	for(int i = 0; i < array1.length; i++) {
    		if (!assertEquals(array1[i], array2[i], eps)) return false;
    	}
    	return true;
    }

    private boolean assertMatrixEquals(Matrix m1, Matrix m2, double eps) {
    	for(int r = 0; r < m1.rows(); r++) {
    		for(int c = 0; c < m1.columns(); c++) {
    			if (m1.get(r, c) -  m2.get(r, c) > eps) {
    				return false;
    			};
    		}
    	}
    	return true;
    }
    
    private boolean assertEquals (double a, double b, double e) {
    	if (Math.abs(a-b) < e) {
    		return true;
    	}
    	return false;
    }
    
    private Basic2DMatrix getSubMatrix(Basic2DMatrix matrix, 
    		int rowBegin, int colBegin, int rowNum, int colNum) {
    	double[][] matrixArray = new double[rowNum][colNum];
    	for(int r = 0; r < rowNum; r++) {
    		for(int c = 0; c < colNum; c++) {
    			matrixArray[r][c] = matrix.get(r + rowBegin, c + colBegin);
    			System.out.print(matrixArray[r][c] + ", ");
    		}
    	}
    	return new Basic2DMatrix(matrixArray);
    } 
}
