package job;

import java.util.Random;

// プレイヤー：戦士
public class Fighter extends Player {

	public Fighter(String name)
	{
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 200)+100;   //100~300
		this.str = GetNumber(1, 70)+30;      //30~100
		this.def = GetNumber(2, 70)+30;          //30~100
		this.luck = GetNumber(3,99)+1;       //1~100
		this.agi = GetNumber(4,49)+1;        //1~50
		this.type = "戦士";
		this.abnormality = "";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender,int turnNumber)
	{
        // 与えるダメージを求める
        System.out.println(GetName() + "の攻撃！");
        int damage = CalcDamage(defender);

        Random r = new Random();
        boolean parise = false;
        if(this.abnormality =="parise"){
        	if(r.nextInt(5) == 0)
            	parise = true;
        }

        // 求めたダメージを対象プレイヤーに与える
        if(parise == true){
        	System.out.println(GetName()+"は痺れてうまく攻撃できなかった");
        }else{
        	System.out.print(defender.GetName() + "に" + damage + "のダメージ！       "+"HP:"+defender.GetHP()+" → ");
            defender.Damage(damage);
            System.out.println(defender.GetHP());
        }

        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");
        }
	}

}
