package Model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;

import Config.Alphabet;

import Utils.Check;


public class Hill {
	public ArrayList<Integer> indexUpperCaseEn;
	public SortedMap<Integer, Character> specialEn;
	public ArrayList<Integer> indexUpperCaseDe;
	public SortedMap<Integer, Character> specialDe;
	
	public Hill() {
		this.indexUpperCaseEn = new ArrayList<>();
		this.specialEn = new TreeMap<>();
		this.indexUpperCaseDe = new ArrayList<>();
		this.specialDe = new TreeMap<>();
	}
	
	public ArrayList<char[]> splitString(char[] alphabet, String input, int n, ArrayList<Integer> indexUpper, SortedMap<Integer, Character> special) {
		String notSpecial = "";
		for(int i = 0; i < input.length(); i++) {
			if(Check.contain(alphabet, input.charAt(i))) {
				notSpecial += input.charAt(i);
			}
			if(Character.isUpperCase(input.charAt(i)) && Check.contain(alphabet, Character.toLowerCase(input.charAt(i)))) {
				notSpecial += Character.toLowerCase(input.charAt(i));
			}
			
			if(!Check.contain(alphabet, Character.toLowerCase(input.charAt(i)))) {
				special.put(i, Character.toLowerCase(input.charAt(i)));
			}
			if(Character.isUpperCase(input.charAt(i))) {
				indexUpper.add(i);
			}
		}
        ArrayList<char[]> resultList = new ArrayList<>();
        char[] charArray = notSpecial.toCharArray();
        for (int i = 0; i < charArray.length; i += n) {
        	int endIndex = i + n;
            char[] subArray = new char[n];
        	if(endIndex < charArray.length) {
        		System.arraycopy(charArray, i, subArray, 0, endIndex - i);
                resultList.add(subArray);
        	}else {
        		int index = 0;
        		for(int j = i; j < charArray.length; j++) {
        			subArray[index] = charArray[j];
        			index++;
        		}
        		for(int k = charArray.length; k < endIndex; k++) {
        			subArray[index] = 'z';
        			index++;
        		}
        		resultList.add(subArray);
        	}
        }
        return resultList;
    }
	
	
	public ArrayList<int[]> indexElementsOfToken(char[] alphabet, String input, int n, ArrayList<Integer> indexUpper, SortedMap<Integer, Character> special){
		ArrayList<int[]> result = new ArrayList<>();
		ArrayList<char[]> splitInput = splitString(alphabet, input, n, indexUpper, special);
		for (char[] subArray : splitInput) {
		}
		for(int i = 0; i < splitInput.size(); i++) {
			char[] token = splitInput.get(i);
			int[] indexOfToken = new int[token.length];
			for(int j = 0; j < token.length; j++) {
				if(Check.contain(alphabet, token[j])) {
					indexOfToken[j] = Check.index(alphabet, token[j]);
				}
			}
			result.add(indexOfToken);
		}
		return result;
	}
	
	public String encrypt(char[] alphabet, String input, int n, int[][] keyMatrix) {
		String result = "";
		ArrayList<int[]> order = indexElementsOfToken(alphabet, input, n, indexUpperCaseEn, specialEn);
		for (int[] subArray : order) {
			}
		ArrayList<int[]> arrayMutilply = new ArrayList<>();
		for(int[] tokenOrder : order) {
			int[] resultMutilply = multiply(alphabet, tokenOrder, keyMatrix);
			arrayMutilply.add(resultMutilply);
		}
		int[] convert1D = convertArrayListToOneDArray(arrayMutilply);
		for(int i = 0; i < convert1D.length; i++) {
			result += alphabet[convert1D[i]];
		}
		return result;
	}
	
	public String encryptFinal(char[] alphabet, String input, int n, int[][] keyMatrix) {
		indexUpperCaseEn.clear();
		specialEn.clear();
		StringBuilder encrypt = new StringBuilder(encrypt(alphabet, input, n, keyMatrix)); 
		StringBuilder newEn = new StringBuilder();
		for(Entry<Integer, Character> entry : specialEn.entrySet()) {
			newEn = encrypt.insert(entry.getKey(), entry.getValue().toString());
			encrypt = newEn;
		}
		for(int i = 0; i < indexUpperCaseEn.size(); i++) {
			encrypt.setCharAt(indexUpperCaseEn.get(i), Character.toUpperCase(encrypt.charAt(indexUpperCaseEn.get(i))));
		}
		return encrypt.toString();
	}
	
	public String decrypt(char[] alphabet, String input, int n, int[][] keyMatrix) {
		indexUpperCaseDe.clear();
		specialDe.clear();
		String result = "";
		int[][] keyMatrixDe = keyMatrixForDe(alphabet, keyMatrix);
		ArrayList<int[]> order = indexElementsOfToken(alphabet, input, n, indexUpperCaseDe, specialDe);
		for (int[] subArray : order) {
			}
		ArrayList<int[]> arrayMutilply = new ArrayList<>();
		for(int[] tokenOrder : order) {
			int[] resultMutilply = multiply(alphabet, tokenOrder, keyMatrixDe);
			arrayMutilply.add(resultMutilply);
		}
		int[] convert1D = convertArrayListToOneDArray(arrayMutilply);
		for(int i = 0; i < convert1D.length; i++) {
			result += alphabet[convert1D[i]];
		}
		return result;
	}
	
	public String decryptFinal(char[] alphabet, String input, int n, int[][] keyMatrix) {
		StringBuilder decrypt = new StringBuilder(decrypt(alphabet, input, n, keyMatrix)); 
		for(Entry<Integer, Character> entry : specialDe.entrySet()) {
			StringBuilder newDe = decrypt = decrypt.insert(entry.getKey(), entry.getValue().toString());
			decrypt = newDe;
		}
		for(int i = 0; i < indexUpperCaseDe.size(); i++) {
			decrypt.setCharAt(indexUpperCaseDe.get(i), Character.toUpperCase(decrypt.charAt(indexUpperCaseDe.get(i))));
		}
		return decrypt.toString();
	}
	
	public int[] multiply(char[] alphabet, int[] vector, int[][] matrix) {
        if (vector.length != matrix.length) {
            throw new IllegalArgumentException("Number of elements in the vector must be equal to the number of rows in the matrix");
        }
        int[] result = new int[matrix[0].length];
        int size = matrix.length;
        int[] resultVector = new int[size];
        for (int i = 0; i < size; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                sum += matrix[i][j] * vector[j];
            }
            resultVector[i] = sum;
        }
        for (int i = 0; i < resultVector.length; i++) {
            result[i] = resultVector[i] % alphabet.length;
        }
        return result;
    }
	
	public int[] convertArrayListToOneDArray(ArrayList<int[]> twoDArray) {
        int totalElements = 0;
        for (int[] array : twoDArray) {
            totalElements += array.length;
        }
        int[] oneDArray = new int[totalElements];
        int index = 0;
        for (int[] array : twoDArray) {
            for (int value : array) {
                oneDArray[index++] = value;
            }
        }
        return oneDArray;
    }
	
	public int calculateDeterminant(int[][] matrix) {
        RealMatrix realMatrix = new Array2DRowRealMatrix(matrix.length, matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                realMatrix.setEntry(i, j, matrix[i][j]);
            }
        }
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        return (int) luDecomposition.getDeterminant();
    }

	 public int findModularInverse(int number, int modulus) {
        number = Math.floorMod(number, modulus);
        for (int x = 1; x < modulus; x++) {
            if (Math.floorMod(number * x, modulus) == 1) {
                return x;
            }
        }
        return -1;
    }
	
	public int[][] phuDaiSo2x2(int[][] matrix) {
        int a = matrix[0][0];
        int b = matrix[0][1];
        int c = matrix[1][0];
        int d = matrix[1][1];
        int determinant = a * d - b * c;
        if (determinant == 0) {
            throw new IllegalArgumentException("Ma trận không khả nghịch.");
        }
        int[][] inverseMatrix = {
                {d, -b},
                {-c, a}
        };
        return inverseMatrix;
    }
	
	public boolean isGCDOne(int a, int b) {
	    while (b != 0) {
	        int temp = b;
	        b = a % b;
	        a = temp;
	    }
	    return a == 1;
	}
	
	public int[][] multiplyMatrixByScalarAndModule(char[] alphabet, int[][] matrix, int scalar, int module) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = Math.floorMod(matrix[i][j] * scalar, alphabet.length) ;
            }
        }
        return result;
    }
	
	public int[][] keyMatrixForDe(char[] alphabet ,int[][] key){
		int dinhThuc = calculateDeterminant(key);
		if(isGCDOne(dinhThuc, alphabet.length) || modInverse(dinhThuc, alphabet.length) != -1) {
			int x = findModularInverse(dinhThuc, alphabet.length);
			int[][] phuDaiSo = phuDaiSo2x2(key);
			return multiplyMatrixByScalarAndModule(alphabet, phuDaiSo, x, alphabet.length);
		}else {
			return null;
		}
	}
	public int[][] convertToMatrix(String input) {
        int[][] matrix = new int[2][2];
        String[] numbers = input.split(" ");
        if (numbers.length != 4) {
            return null;
        }
        for (int i = 0; i < numbers.length; i++) {
            try {
                int num = Integer.parseInt(numbers[i]);
                matrix[i / 2][i % 2] = num;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return matrix;
    }
	public int modInverse(int number, int module) {
        BigInteger n = BigInteger.valueOf(number);
        BigInteger m = BigInteger.valueOf(module);
        BigInteger inverse = n.modInverse(m);
        return inverse.intValue();
    }

	public static void main(String[] args) {
		
	}
}
