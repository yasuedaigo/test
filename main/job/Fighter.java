package main.job;

import main.tactics.*;
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
	protected void makeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		this.hp = getNumber(0, 200)+100;   //100~300
		this.str = getNumber(1, 70)+30;      //30~100
		this.def = getNumber(2, 70)+30;          //30~100
		this.luck = getNumber(3,99)+1;       //1~100
		this.agi = getNumber(4,49)+1;        //1~50
		this.type = "戦士";
		this.abnormality = "";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender,int turnnumber)
	{
        // 与えるダメージを求める
        System.out.println(getName()+"の攻撃！");
        int damage = calcDamage(defender);

        Random r = new Random();
        boolean isParise = false;
        if(this.abnormality =="parise"){
        	if(r.nextInt(5) == 0)
            	isParise = true;
        }

        // 求めたダメージを対象プレイヤーに与える
        if(isParise == true){
        	System.out.println(getName()+"は痺れてうまく攻撃できなかった");
        }else{
        	System.out.print(defender.getName() + "に" + damage + "のダメージ！       "+"HP:"+defender.getHP()+" → ");
            defender.damage(damage);
            System.out.println(defender.getHP());
        }

        // 倒れた判定
        if (defender.getHP() <= 0) {
            System.out.println(defender.getName() + "は力尽きた...");
        }
	}
	
	@Override
	public Tactics targetLogic(){
		Tactics ai = new FighterTactics();
		return ai;
	}

}
