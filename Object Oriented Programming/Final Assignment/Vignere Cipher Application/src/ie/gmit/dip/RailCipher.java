package ie.gmit.dip;

import java.io.Serializable;

public class RailCipher implements Serializable {
	private static final long serialVersionUID = 777L;
	private String inputText;
	private String outputText;
	private String source;
	private String outputFile;
	private int[] encryptionKeys;
	private char[][] railFence;
	private String sourceType;
	private String encryptionStatus;

	public RailCipher(String inputText, String outputText, String source, String outputFile, int[] encryptionKeys,
			char[][] railFence, String sourceType, String encryptionStatus) {
		this.inputText = inputText;
		this.outputText = outputText;
		this.source = source;
		this.outputFile = outputFile;
		this.encryptionKeys = encryptionKeys;
		this.railFence = railFence;
		this.sourceType = sourceType;
		this.encryptionStatus = encryptionStatus;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public int[] getEncryptionKeys() {
		return encryptionKeys;
	}

	public void setEncryptionKeys(int[] encryptionKeys) {
		this.encryptionKeys = encryptionKeys;
	}

	public char[][] getRailFence() {
		return railFence;
	}

	public void setRailFence(char[][] railFence) {
		this.railFence = railFence;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getEncryptionStatus() {
		return encryptionStatus;
	}

	public void setEncryptionStatus(String encryptionStatus) {
		this.encryptionStatus = encryptionStatus;
	}
}
