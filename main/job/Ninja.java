package main.job;

import main.tactics.*;

// プレイヤー：忍者
//麻痺の影響をうけない
//25%の確率で攻撃をかわす
//1ターン目のみ攻撃が必ずクリティカル攻撃になる
//戦士に攻撃するとき戦士のDEFを半分にする
public class Ninja extends Player {

	// =======================
	// コンストラクタ
	// =======================
	public Ninja(String name)
	{
		super(name);
	}


	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter()
	{
		// 忍者のパラメータを名前から生成する
		this.hp = getNumber(0, 100)+100;   //100~200
		this.str = getNumber(1, 50)+20;      //20~70
		this.def = getNumber(2, 70)+20;          //20~70
		this.luck = getNumber(3,99)+1;       //1~100
		this.agi = getNumber(4,40)+40;        //40~80
		this.type = "忍者";
		this.abnormality = "";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender,int turnNumber)
	{
        // 与えるダメージを求める
		//最初のターンのみ相手の防御無視の攻撃をする
		//戦士に攻撃したときのみ相手のDEFを半分にしてダメージ計算する
        System.out.println(getName() + "の攻撃！");
        int damage;
        if(turnNumber == 1){
        	damage = getSTR();
        }else if(defender.getType() == "戦士"){
        	int stockDEF = defender.getDEF();
        	defender.setDEF(defender.getDEF()/2);
            damage = calcDamage(defender);
            defender.setDEF(stockDEF);
        }else{
        	damage = calcDamage(defender);
        }


        // 求めたダメージを対象プレイヤーに与える
        System.out.print(defender.getName() + "に" + damage + "のダメージ！       "+"HP:"+defender.getHP()+" → ");
        defender.damage(damage);
        System.out.println(defender.getHP());

        // 倒れた判定
        if (defender.getHP() <= 0) {
            System.out.println(defender.getName() + "は力尽きた...");

        }
	}
	
	@Override
	public Tactics targetLogic(){
		Tactics ai = new NinjaTactics();
		return ai;
	}

}
