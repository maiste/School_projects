package ast;

import java.awt.*;
import java.lang.Exception;
import exception.ParserException;
import expression.Expression;

/**
 * Classe correspondant à la fonction fillRect de Graphics
 * @author DURAND-MARAIS
 */
public class FillRect extends AST {
    Expression exp1;
    Expression exp2;
    Expression exp3;
    Expression exp4;	
    Color color;
	
    /**
     * on construit un AST correspondant à la fonction fillRect
     * @param  line   ligne de l'expression dans le fichier
     * @param  column colonne de l'expression dans le fichier
     * @param  exp1   premier argument de la fonction
     * @param  exp2   deuxième argument de la fonction
     * @param  exp3   troisème argument de la fonction
     * @param  exp4	  quatrième argument de la fonction
     * @param  color  couleur
     */
    public FillRect(int line, int column, Expression exp1, Expression exp2, Expression exp3, Expression exp4, Color color){
        super(line, column);
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.exp4 = exp4;
        this.color = color;
    }

    @Override
    public void verifyAll(ValueEnv env) throws Exception{
        exp1.setType(env);
        exp2.setType(env);
        exp3.setType(env);
        exp4.setType(env);

        exp1.verifyType(env);
        exp2.verifyType(env);
        exp3.verifyType(env);
        exp4.verifyType(env);


        if(exp1.getType() != Type.INT || exp2.getType() != Type.INT || exp3.getType() != Type.INT || exp4.getType() != Type.INT)
            throw new ParserException("Il y a un problème de typage", line, column);
    }

    @Override
    public void exec(Graphics2D g2d, ValueEnv val) throws Exception {
        int x = exp1.evalInt(val);
        int y = exp2.evalInt(val);
        int w = exp3.evalInt(val);
        int h = exp4.evalInt(val);
        g2d.setColor(color);
        g2d.fillRect(x,y,w,h);
        if(debugMode()) {debug(x,y,w,h);}
    }

    /** Debug */
    public void debug(int x, int y, int w, int h) {
        System.out.println("FillRect => x:" +x+ " y: " +y+ " w: " + w + " h: " + h);
    }
    
}
