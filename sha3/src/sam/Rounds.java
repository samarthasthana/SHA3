package sam;


/**
 *
 * @author Sam
 */
public class Rounds {
    
    private char[] spongy=new char[1600];
    
    private int i_val,j_val;
    
    public int[][] arr_base={{3,2},{1,0}};
    public int[][] arr_ident={{0},{1}};
    public Rounds(){
        i_val=0;
        j_val=0;               
    }
    
    public void set_i(int i){
        i_val=i;
    }
    
    public void set_j(int j){
        j_val=j;
    }
    public int get_i(){
        return i_val;        
    }
    
    public int get_j(){
        return j_val;
    }
    
    public void set_spongy(char[] a){
        spongy=a;
    }
    public char[] get_spongy(){
        return spongy;
    }
    
    public char xor_bit(char a,char b){
        if(a==b){return '0';}
        else{return '1';}
    }
    public char not_bit(char a){
        if(a=='0')return '1';
        else 
            return '0';
    }
    public char and_bit(char a,char b){
        if(a=='0' || b=='0'){
            return '0';
        }else{
            return '1';
        }
    }
    public char[]  hex_to_bin_char(String val){
       String result="";
       char[] val1= new char[val.length()];
       val1=val.toCharArray();
        for(int i=0;i<val1.length;i++){
           switch(val1[i]){
               case '0':
                   result=result+"0000";
                   break;
               case '1':
                   result=result+"0001";
                   break;
               case '2':
                   result=result+"0010";
                   break;
                   
                case '3':
                   result=result+"0011";
                   break;
                case '4':
                   result=result+"0100";
                   break;
                case '5':
                   result=result+"0101";
                   break;
                case '6':
                   result=result+"0110";
                   break;
                case '7':
                   result=result+"0111";
                   break;
                case '8':
                   result=result+"1000";
                   break;
                case '9':
                   result=result+"1001";
                   break;
                case 'A':
                   result=result+"1010";
                   break;
                case 'B':
                   result=result+"1011";
                   break;
                case 'C':
                   result=result+"1100";
                   break;
                case 'D':
                   result=result+"1101";
                   break;
                case 'E':
                   result=result+"1110";
                   break;
                case 'F':
                   result=result+"1111";
                   break;
           }
       }
     return result.toCharArray();
    }
    public void matrix_t(int t){
        int sum=0;
        int[][] temp = new int[2][2];
        int[][] multi = new int[2][2];
        int [][] fin=new int[2][1];
        for(int i=0;i<2;i++){
            System.arraycopy(arr_base[i], 0, temp[i], 0, 2);
        }
        if(t>1){
            for(int j=0;j<t-1;j++){                            
                for(int c=0;c<2;c++){
                    for(int d=0;d<2;d++){
                        for(int k=0;k<2;k++){
                            sum=sum+temp[c][k]*arr_base[k][d];    
                        }                        
                        multi[c][d]=(sum%5);
                        sum=0;
                    }
                }
                for(int g=0;g<2;g++){
                    System.arraycopy(multi[g], 0, temp[g], 0, 2);
                }
            }
        }
        if(t==0){
            set_i(0);
            set_j(1);
        }
        if(t==1){
            set_i(2);
            set_j(0);
        }
        sum=0;
        if(t>1){
            for(int c=0;c<2;c++){
                    for(int d=0;d<1;d++){
                        for(int k=0;k<2;k++){
                            sum=sum+multi[c][k]*arr_ident[k][d];    
                        }
                        fin[c][d]=(sum%5);
                        sum=0;
                    }
            }
            set_i(fin[0][0]);
            set_j(fin[1][0]);
        }
        
    }
    
    public void theta (char[] spon){
        char a1='0',a2='0';
        int index1=0,index2=0;
        for(int k=0;k<64;k++){
            for(int j=0;j<5;j++){
                for(int i=0;i<5;i++){
                    for(int s=0;s<5;s++){
                        if(j-1<0){
                            index1=(((5*s)+4)*64)+k;
                        }else{
                            index1=(((5*s)+(j-1))*64)+k;
                        }
                        if(j+1>4){
                            if(k-1<0){
                            index2=(((5*s)+0)*64)+4;
                            }else{
                            index2=(((5*s)+0)*64)+k-1;    
                            }
                        }else{
                         if(k-1<0){
                             index2=(((5*s)+(j+1))*64)+4;
                         }else{
                             index2=(((5*s)+(j+1))*64)+k-1;
                         }   
                        }
                        a1=xor_bit(a1, spon[index1]);
                        a2=xor_bit(a2, spon[index2]);
                    }
                spon[(((5*i)+j)*64)+k]=xor_bit(spon[(((5*i)+j)*64)+k], xor_bit(a1, a2));
                }                
            }
            
        }
     set_spongy(spon);
    }
    public void rho (char[] spon){
        int i=0,j=0;
        int index=0;
        for(int k=0;k<64;k++){
            for(int t=0;t<24;t++){
                matrix_t(t);
                i=get_i();
                j=get_j();
                index=(k-(t+1)*(t+2)/2)%64;                
                if(index<0){
                    index=index+64;
                }//                
                spon[((i*5+ j)*64)+k]=spon[((i*5+ j)*64)+index];
            }
        }
        set_spongy(spon);
    }
    public void pi (char[] spon){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<64;k++){
                    spon[(5*j+((2*i+3*j)%5)*64+k)]=spon[((i*5+ j)*64)+k];
                }
            }
        }
        set_spongy(spon);
    }
    public void xi (char[] spon){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<64;k++){
                    spon[(5*i+j)*64+k]=xor_bit(spon[(5*i+j)*64+k],and_bit(not_bit(spon[(5*i+((j+1)%5))*64+k]), spon[(5*i+((j+2)%5))*64+k]) );
                }
            }
        }
        set_spongy(spon);
    }
    public void iota (char[] spon, int round){
        char[][] constants=new char [24][64];
        constants[0]=hex_to_bin_char("0000000000000001");
        constants[1]=hex_to_bin_char("0000000000008082");
        constants[2]=hex_to_bin_char("800000000000808A");
        constants[3]=hex_to_bin_char("8000000080008000");
        constants[4]=hex_to_bin_char("000000000000808B");
        constants[5]=hex_to_bin_char("0000000080000001");
        constants[6]=hex_to_bin_char("8000000080008081");
        constants[7]=hex_to_bin_char("8000000000008009");
        constants[8]=hex_to_bin_char("000000000000008A");
        constants[9]=hex_to_bin_char("0000000000000088");
        constants[10]=hex_to_bin_char("0000000080008009");
        constants[11]=hex_to_bin_char("000000008000000A");
        constants[12]=hex_to_bin_char("000000008000808B");
        constants[13]=hex_to_bin_char("800000000000008B");
        constants[14]=hex_to_bin_char("8000000000008089");
        constants[15]=hex_to_bin_char("8000000000008003");
        constants[16]=hex_to_bin_char("8000000000008002");
        constants[17]=hex_to_bin_char("8000000000000080");
        constants[18]=hex_to_bin_char("000000000000800A");
        constants[19]=hex_to_bin_char("800000008000000A");
        constants[20]=hex_to_bin_char("8000000080008081");
        constants[21]=hex_to_bin_char("8000000000008080");
        constants[22]=hex_to_bin_char("0000000080000001");
        constants[23]=hex_to_bin_char("8000000080008008");
        int i=0,j=0;
        for(int k=0;k<64;k++){
            spon[(5*i+j)*64+k]=xor_bit(spon[(5*i+j)*64+k], constants[round][k]);
        }
    }
    
    public char[] round_work(char[] blk){
        set_spongy(blk);       
        for(int r=0;r<24;r++){
            theta(get_spongy());
            rho(get_spongy());
            pi(get_spongy());
            xi(get_spongy());
            iota(get_spongy(),r);
        }
        return get_spongy();
    }
}
