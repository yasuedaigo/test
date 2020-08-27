package job;
import java.math.BigInteger;
import java.util.Random;
import java.security.MessageDigest;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;

	protected int luck;

	protected int agi;

	protected int mp;

	protected String type;         //ジョブ

	protected String abnormality;  //状態異常

	protected int team;      //所属チーム 1or2

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 * @param name : プレイヤー名
	 */
	public Player(String name) {
		this.name = name;

		// キャラクターのパラメータ生成
		MakeCharacter();
	}


	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 * @return プレイヤー名
	 */
	public String GetName()
	{
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 * @return 現在HP
	 */
	public int GetHP()
	{
		return this.hp;
	}

	/**
	 * 攻撃力を取得する
	 * @return 攻撃力
	 */
	public int GetSTR()
	{
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * @return 防御力
	 */
	public int GetDEF()
	{
		return this.def;
	}

	public int GetLUCK()
	{
		return this.luck;
	}

	public int  GetAGI()
	{
		return this.agi;
	}

	public String GetTYPE()
	{
		return this.type;
	}

	public String GetABNORMALITY()
	{
		return this.abnormality;
	}

	public int GetTEAM()
	{
		return this.team;
	}

	public void SetTEAM(int x){
		this.team = x;
	}

	public void SetDEF(int y){
		this.def = y;
	}

	public void SetSTR(int y){
		this.str = y;
	}

	public void SetLUCK(int y){
		this.luck = y;
	}

	public void SetAGI(int y){
		this.agi = y;
	}
	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter()
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * @param index : 何番目の数値を取り出すか
	 * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int GetNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);

			// 取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// エラー
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 現在のステータスを System.out で表示する
	 */
	public void PrintStatus()
	{
		System.out.println(this.GetName()+"  HP="+this.GetHP()+"  STR="+this.GetSTR()+"  DEF"+this.GetDEF()+"  "+this.GetABNORMALITY());
		System.out.println("agi="+GetAGI()+"     team"+GetTEAM());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void Attack(Player defender,int turnNumber)
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int CalcDamage(Player target)
    {
    	Random r = new Random();
    	if(GetLUCK() <= r.nextInt(101)){
    		int damage = GetSTR();
    		return damage;
    	}
        int damage = GetSTR() - target.GetDEF();
        if (damage < 0)
        {
               damage = 0;
        }
    	return damage;
    }

    /**
     * ダメージを受ける
     * @param damage : ダメージ値
     */
    public void Damage(int damage)
    {
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.GetHP() - damage, 0);
    }
}