package classes;
import java.util.*;
//import classes.Node;

/** ExpressionTree
* @author Salvador Moya Bartolome (salvador.moya@estudiantat.upc.edu)
*/
/*Codi que genera una llista dels elements de l'expressió booleana amb un algorisme Shunting Yard modificat obtingut de la següent pàgina:
https://codereview.stackexchange.com/questions/89967/boolean-expressions-from-infix-to-postfix-notation-using-dijkstras-shunting-yard

*/


public class ExpressionTree {

    /**
     * The name of AND operator.
     */
    public static final String AND = "&";

    /**
     * The name of NOT operator.
     */
    public static final String NOT = "!";

    /**
     * The name of OR operator.
     */
    public static final String OR = "|";


    /**
     * The default operator precedence: NOT, AND, OR.
     */
    private static final Map<String, Integer> defaultPrecedenceMap;

    static {
        defaultPrecedenceMap = new HashMap<>();
        defaultPrecedenceMap.put(NOT, 1);
        defaultPrecedenceMap.put(AND, 2);
        defaultPrecedenceMap.put(OR, 3);
    }

    /**
     * Calls for Shunting Yard algorithm with default operator precedence.
     * 
     * @param  tokens the tokens in infix notation.
     * @return the list of tokens in postfix notation, or <code>null</code> if
     * the input token list is invalid.
     */
    public static List<String> shuntingYard(final Deque<String> tokens) {
        return shuntingYard(tokens, defaultPrecedenceMap);
    }

    /**
     * Returns a list of tokens in postfix notation. If the input list of tokens
     * is invalid (by having, say, incorrect parenthesization), 
     * <code>null</code> is returned.
     * 
     * @param tokens        the tokens in infix notation.
     * @param precedenceMap the operator precedence map.
     * @return the list of tokens in postfix notation, or <code>null</code> if
     *         the input token list is invalid.
     */
    public static List<String> 
    shuntingYard(final Deque<String> tokens,
                 final Map<String, Integer> precedenceMap) {
        final Deque<String> operatorStack = new LinkedList<>();
        final Deque<String> outputQueue = new LinkedList<>();
        String previousToken = "";

        while (!tokens.isEmpty()) {
            final String currentToken = tokens.removeFirst();

            if (isVariableOrConstantName(currentToken)) {
                outputQueue.add(currentToken);
            } else if (isOperator(currentToken)) {
                while (!operatorStack.isEmpty()) {
                    if (isOperator(operatorStack.getLast())
                            && precedenceCompare(operatorStack.getLast(),
                                                 currentToken,
                                                 precedenceMap)) {
                        outputQueue.addLast(operatorStack.removeLast());
                    } else {
                        break;
                    }
                }

                operatorStack.addLast(currentToken);
            } else if (currentToken.equals("(")) {
                if (isVariableOrConstantName(previousToken)) {
                    return null;
                }

                operatorStack.addLast("(");
            } else if (currentToken.equals(")")) {
                if (!isVariableOrConstantName(previousToken)) {
                    return null;
                }

                while (!operatorStack.isEmpty() && 
                       !operatorStack.getLast().equals("(")) {
                    outputQueue.addLast(operatorStack.removeLast());
                }

                if (operatorStack.isEmpty()) {
                    // Structure is invalid.
                    return null;
                } else {
                    // remove left bracket '('
                    operatorStack.removeLast();
                }
            }  
              else {
                throw new IllegalStateException(
                        "Could not recognize a token: " + currentToken);
            }
            previousToken = currentToken;
        }

        while (!operatorStack.isEmpty()) {
            final String operator = operatorStack.removeLast();

            // Parenthesis structure is invalid.
            if (operator.equals("(") || operator.equals(")")) {
                return null;
            }

            outputQueue.addLast(operator);
        }

        return new ArrayList<>(outputQueue);
    }

    /**
     * Compares <code>stackTopToken</code> and <code>token</code> by their 
     * precedences.
     * 
     * @param  stackTopToken the token at the top of operator stack.
     * @param  token         the token to compare against.
     * @param  precedenceMap the operator precedence map.
     * @return <code>true</code> if the token at the top of the stack precedes
     *         <code>token</code>.
     */
    private static boolean 
    precedenceCompare(final String stackTopToken,
                      final String token,
                      final Map<String, Integer> precedenceMap) {
        return precedenceMap.get(stackTopToken) < precedenceMap.get(token);
    }

    /**
     * Checks whether the input token is a variable or constant name.
     * 
     * @param  token the token to check.
     * @return <code>true</code> if and only if <code>token</code> is a constant
     *         (such as "0" or "1") or any other token that cannot be recognized
     *         as an operator, parenthesis, or a constant.
     */
    private static boolean isVariableOrConstantName(final String token) {
        if (isOperator(token)) {
            return false;
        }

        if (token.equals("(")) {
            return false;
        }

        if (token.equals(")")) {
            return false;
        }

        return !token.isEmpty();
    }
    /**
     * Checks whether the input token denotes an operator such as AND, NOT, OR.
     * 
     * @param  token the token to check.
     * @return <code>true</code> if and only if <code>token</code> is an 
     *         operator.
     */
    private static boolean isOperator(final String token) {
        switch (token) {
            case AND:
            case NOT:
            case OR:
                return true;

            default:
                return false;
        }
    }

    /**
     * Splits the input text into a list of tokens.
     * 
     * @param  text the text to split.
     * @return the list of tokens.
     */
    private static Deque<String> toTokenList(final String text) {
        final Deque<String> tokenList = new ArrayDeque<>();

        int index = 0;

        while (index < text.length()) {
            if (text.substring(index).startsWith(AND)) {
                index += AND.length();
                tokenList.add(AND);
            } else if (text.substring(index).startsWith(OR)) {
                index += OR.length();
                tokenList.add(OR);
            } else if (text.substring(index).startsWith(NOT)) {
                index += NOT.length();
                tokenList.add(NOT);
            } else if (text.charAt(index) == '(') {
                ++index;
                tokenList.add("(");
            } else if (text.charAt(index) == ')') {
                ++index;
                tokenList.add(")");
            } else if (text.charAt(index) == '0') {
                ++index;
                tokenList.add("0");
            } else if (text.charAt(index) == '1') {
                ++index;
                tokenList.add("1");
            } else {
                int index2 = index;

                while (index2 < text.length() 
                        && !Character.isWhitespace(text.charAt(index2))
                        && text.charAt(index2) != '('
                        && text.charAt(index2) != ')') {
                    ++index2;
                }

                final String variableName = text.substring(index, index2);
                index += variableName.length();
                tokenList.add(variableName);
            }

            index = advancePastWhitespace(text, index);
        }

        return tokenList;
    }

    /**
     * Advances the input index to a position with non-whitespace character.
     * 
     * @param  text  the text.
     * @param  index the index.
     * @return the index no less than <code>index</code> at which text contains
     *         a non-whitespace character.
     */
    private static int advancePastWhitespace(final String text, int index) {
        while (index < text.length() 
                && Character.isWhitespace(text.charAt(index))) {
            ++index;
        }

        return index;
    }

    /**
     * The demo program.
     * 
     * @param args ignored.
     */
    public Node ConsultaExpressioBooleana(String line) {

            final List<String> postfixTokenList = 
                    shuntingYard(toTokenList(line));

            if (postfixTokenList == null) {
                System.out.println("Invalid token sequence!");
            } 
            
            System.out.println(postfixTokenList);
            Node r = BooleanTreeGenerator(postfixTokenList);
            return r;
        }
        
     public static boolean isLogicOperator(String ch){
        if(ch=="|" || ch=="&"|| ch=="!"){
            return true;
        }
        return false;
    }
    public static Node ExpressionTree(List <String> postfix){
        Stack<Node> st = new Stack<Node>();
        Node t1,t2,temp;
        ListIterator<String> iter = postfix.listIterator();
 
        //System.out.print("Printing postfix\n");
        while (iter.hasNext()){
        String value = iter.next();
       // System.out.print(value+"\n");
            if(value.charAt(0) == '{'){

                while (value.charAt(value.length()-1) != '}'){
                //System.out.print("Creating set of words"+value+"\n");
                value+=" "+iter.next();
                    
                }
                //System.out.print("Set = "+value+"\n");
                temp = new Node(value);
                st.push(temp);
            }
            
            else if(value.charAt(0) == '"'){

                while (value.charAt(value.length()-1) != '"'){
                
                value+=iter.next();
                    
                }
                temp = new Node(value);
                st.push(temp);
            }
            
            else if(!isLogicOperator(value)){
            
                temp = new Node(value);
                st.push(temp);
            }
            else{
                temp = new Node(value);
 
                if (value != "!"){
                    t1 = st.pop();
                temp.right = t1;
                }
                t2 = st.pop();
                temp.left = t2;
                st.push(temp);
            }
 
        }
        temp = st.pop();
        return temp;
    }
    static void inorder(Node root){
        if(root==null) return;
 
        inorder(root.left);
        //System.out.print(root.data+ "\n");
        inorder(root.right);
    }
    
    public Node BooleanTreeGenerator(List<String> postfix) {
        
        Node r = ExpressionTree(postfix);
        //System.out.print("printing tree\n");
        //inorder(r);
        return r;
    }   
}








