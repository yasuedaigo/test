package main.job;

import java.util.Random;
import main.tactics.*;

// プレイヤー：魔法使い
//mpに応じて場合使える魔法をランダムに使用。使える魔法がない場合通常攻撃
public class Wizard extends Player {
	Random r = new Random();

	public Wizard(String name)
	{
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter()
	{
		// 魔法使いのパラメータを名前から生成する
		this.hp = getNumber(0, 100)+50;   //50~150
		this.str = getNumber(1, 50);      //1~50
		this.def = getNumber(2, 50);          //1~50
		this.luck = getNumber(3,100);       //1~100
		this.agi = getNumber(4,40)+20;        //20~60
		this.mp = getNumber(5,50)+30;      //30~80
		this.type = "魔法使い";
		this.abnormality ="";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender,int turnNumber)
	{
		System.out.print(getName() + "の攻撃！");
        //麻痺判定 麻痺しているとき20%の確率で攻撃に失敗
		Random r = new Random();
        boolean parise = false;
        if(this.abnormality =="parise"){
        	if(r.nextInt(5) == 0)
            	parise = true;
        }

        if(parise == true){
        	System.out.println("   "+getName()+"は痺れてうまく攻撃できなかった");
        }else{
        	if(this.mp >= 20){
    			int rndmagic = r.nextInt(2);            //使える魔法が2つあるときにランダムにどちらかを使えるようにする変数rndmagic
    			switch(rndmagic){                       //mpが20以上のときファイアかサンダーをランダムで使用
    			   case 0:this.fire(defender);
    			          break;
    			   case 1:this.thunder(defender);
    			          break;
    			}
    		    }else if(this.mp < 20||this.mp >= 10){
    				 this.fire(defender);               //mpが10以上20以下のときファイアを使用
    			}else{
    				//通常攻撃
    				// 与えるダメージを求める
    		        System.out.print(getName() + "の攻撃！");
    		        int damage = calcDamage(defender);

    		        // 求めたダメージを対象プレイヤーに与える
    		        System.out.print("\r\n"+defender.getName() + "に" + damage + "のダメージ！       "+"HP:"+defender.getHP()+" → ");
    	            defender.damage(damage);
    	            System.out.println(defender.getHP());
    			}
        }

		        // 倒れた判定
         if (defender.getHP() <= 0) {
		     System.out.println(defender.getName() + "は力尽きた...");
		 }
    }

	public void thunder(Player defender){
		//サンダー
        System.out.println("   "+getName() + "のサンダー！");
        int damage = r.nextInt(21)+10;         //10~30の防御無視ダメージ
        this.mp = this.mp-20;          //mp消費-20

        System.out.print(defender.getName() + "に" + damage + "のダメージ！       "+"HP:"+defender.getHP()+" → ");
        defender.damage(damage);
        System.out.println(defender.getHP());
	}


	public void fire(Player defender){
		//ファイア
        System.out.println("   "+getName() + "のファイア！");
        int damage = r.nextInt(21)+10;         //10~30の防御無視ダメージ
        this.mp = this.mp-10;          //mp消費-10

        System.out.print(defender.getName() + "に" + damage + "のダメージ！       "+"HP:"+defender.getHP()+" → ");
        defender.damage(damage);
        System.out.println(defender.getHP());
	}

	@Override
	public Tactics targetLogic(){
		Tactics ai = new WizardTactics();
		return ai;
	}
}
