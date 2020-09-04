package main.job;

import java.util.Random;
import main.tactics.*;

// プレイヤー：僧侶
//最初のターンのみパライズかポイズンを使用
//HPが50以上減っていたらヒール、減っていなければ通常攻撃。
public class Priest extends Player {

	public Priest(String name)
	{
		super(name);
	}


	 //名前(name)からキャラクターに必要なパラメータを生成する

	@Override
	protected void makeCharacter()
	{
		// 僧侶のパラメータを名前から生成する
		this.hp = getNumber(0, 120)+80;   //80~200
		this.str = getNumber(1, 60)+10;      //10~70
		this.def = getNumber(2, 60)+10;          //10~70
		this.luck = getNumber(3,99)+1;       //1~100
		this.agi = getNumber(4,40)+20;        //20~60
		this.mp = getNumber(5,30)+20;      //20~50
		this.type = "僧侶";
		this.abnormality = "";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void attack(Player defender,int turnNumber)
	{
		Random r = new Random();
        boolean isParise = false;
        if(this.abnormality =="parise"){
        	if(r.nextInt(5) == 0)
            	isParise = true;
        }

        ////麻痺判定 麻痺しているとき20%の確率で攻撃に失敗
        if(isParise == true){
        	System.out.println(getName()+"は痺れてうまく攻撃できなかった");
        }else{
        	int rndmagic;
    		//最初のターンのみパライズかポイズンを使用
    		//攻撃対象が味方だった場合ヒールを使用
    		if(turnNumber == 1){
                 rndmagic = r.nextInt(2);
                 switch(rndmagic){
                 case 0:this.parise(defender);
                        break;
                 case 1:this.poison(defender);
                        break;
                 }
    		}else if(defender.getTeam() == this.getTeam()){
                 this.heal(defender);
            }
    		else
    		{
    			//通常攻撃
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

    public void heal(Player defender)
    {
	     System.out.print(getName() + "のヒール！             ");
	     System.out.println(getName() + "は50回復！");
	     defender.hp = defender.hp+50;         //初期HPから50以上ダメージを受けていた場合
	     this.mp = this.mp-20;         //ヒール使用 HP50回復、消費mp20
    }

    public void parise(Player defender)
    {
	     defender.abnormality="parise";
	     System.out.println(getName() + "のパライズ！");
	     System.out.println(defender.getName() + "は麻痺した！");
    }

    public void poison(Player defender)
    {
	     defender.abnormality="poison";
	     System.out.println(getName() + "のポイズン！");
	     System.out.println(defender.getName() + "は毒状態になった！");
    }
    @Override
    public Tactics targetLogic(){
		Tactics ai = new PriestTactics();
		return ai;
	}

}