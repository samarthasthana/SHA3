package sam;


/**
 *
 * @author Sam
 */
public class Sponge {
    
    private char[] spon=new char[1600];
    public char[] cap=new char[512];
    
    public Sponge(){
        for(int i=0;i<1600;i++){
            spon[i]='0'; // initializing the Sponge to 1600^0's
        }
        for(int i=0;i<512;i++){
            cap[i]='0';
        }
    }
    public void spon_set(char[] set) throws InternalError{
        if(set.length !=1600){
            throw new InternalError("Length of value passed in setter is not valid");
        }
        System.arraycopy(set, 0, spon, 0, 1600);            
    }
    
    public char[] spon_get(){        
        return spon;
    }
    
    public void xor_spon(char[] block){
        char[] new_sponge=new char[1600];
        new_sponge=spon_get();
        for(int i=0;i<block.length;i++){
            if(block[i]==new_sponge[i]){
                new_sponge[i]='0';
            }else{
                new_sponge[i]='1';
            }
        }
        spon_set(new_sponge);
    }
    
    public char[] slice_msg(int index,String msg_bin){
        if(msg_bin.length()==0){
            throw new IllegalArgumentException("MSG sent is of zero length");
        }
        char[] slice=new char[1088];
        char[] msg_char=new char[msg_bin.length()];
        msg_char=msg_bin.toCharArray();
        for(int i=0;i<1088;i++){
            slice[i]=msg_char[(index*1088)+i];
        }
        return slice;
    }
    
    public char[] work_sponge(String msg_bin,int bin_size){
        Rounds objR1=new Rounds();
        char[] block=new char[1600];
        char[] slice=new char[1088];        
        for(int i=0;i<bin_size ; i++){// running for the number of blocks 
            slice=slice_msg(i, msg_bin); //slices the msg into a 1088 slice block
            for(int j=0;j<1600;j++){ // adding 512 zeroes to 1088 slice to make it a block
                if(j<1088){block[j]=slice[j];}
                else{block[j]='0';}
            }            
            xor_spon(block);// XOR-D the sponge with the block as it comes in
            spon_set(objR1.round_work(spon_get()));
        }// end of processing of the blocks        
        return spon_get();
    }
}
