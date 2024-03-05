package Controller;

import View.Home;

public class ControllerAll {
	private Home homeView;
	private DESController DES;
	private AESController AES;
	private TwofishController Twofish;
	private VigenereController vigenere;
	private HillController hill;
	private CreateKeyAndIVController createKeyAndIV;
	private HashController hash;
	private RSAController RSA;
	private CKDTController ckdt;
	public ControllerAll(Home homeView) {
		super();
		this.homeView = homeView;
		this.DES = new DESController(homeView);
		this.AES = new AESController(homeView);
		this.Twofish = new TwofishController(homeView);
		this.vigenere = new VigenereController(homeView);
		this.hill = new HillController(homeView);
		this.createKeyAndIV = new CreateKeyAndIVController(homeView);
		this.hash = new HashController(homeView);
		this.RSA = new RSAController(homeView);
		this.ckdt = new CKDTController(homeView);
	}
}
