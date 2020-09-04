package main.job;

import java.math.BigInteger;
import java.util.Random;
import java.security.MessageDigest;
import main.tactics.*;

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
		makeCharacter();
	}


	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 * @return プレイヤー名
	 */
	public String getName()
	{
		return this.name;
	}
	

	/**
	 * 現在HPを取得する
	 * @return 現在HP
	 */
	public int getHP()
	{
		return this.hp;
	}

	/**
	 * 攻撃力を取得する
	 * @return 攻撃力
	 */
	public int getSTR()
	{
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * @return 防御力
	 */
	public int getDEF()
	{
		return this.def;
	}

	public int getLUCK()
	{
		return this.luck;
	}

	public int  getAGI()
	{
		return this.agi;
	}

	public String getType()
	{
		return this.type;
	}

	public String getAbnormality()
	{
		return this.abnormality;
	}

	public int getTeam()
	{
		return this.team;
	}
	

	public void setTeam(int x){
		this.team = x;
	}

	public void setDEF(int y){
		this.def = y;
	}

	public void setSTR(int y){
		this.str = y;
	}

	public void setLUCK(int y){
		this.luck = y;
	}

	public void setAGI(int y){
		this.agi = y;
	}
	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void makeCharacter()
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * @param index : 何番目の数値を取り出すか
	 * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int getNumber(int index, int max) {
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
	public void printStatus()
	{
		System.out.println(this.getName()+"  HP="+this.getHP()+"  STR="+this.getSTR()+"  DEF"+this.getDEF()+"  "+this.getAbnormality());
		System.out.println("agi="+getAGI()+"     team"+getTeam());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void attack(Player defender,int turnNumber)
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int calcDamage(Player target)
    {
    	Random r = new Random();
    	if(getLUCK() <= r.nextInt(101)){
    		int damage = getSTR();
    		return damage;
    	}
        int damage = getSTR() - target.getDEF();
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
    public void damage(int damage)
    {
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.getHP() - damage, 0);
    }
    
    public Tactics targetLogic(){
    	Tactics ai = new FighterTactics();
    	return ai;
    }
}