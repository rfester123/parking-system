package com.tete.parking;

public class SpotList {

    // Nó da lista duplamente encadeada
    private static class Node {
        Spot s; Node prev, next;
        Node(Spot s){ this.s = s; }
    }

    private Node head, tail;

    public void add(Spot s){
        Node n = new Node(s);
        if(head == null){ head = tail = n; }
        else { tail.next = n; n.prev = tail; tail = n; }
    }

    // ----- Regras de encaixe -----
    private boolean canFit(Vehicle v, Spot s){
        if(v instanceof Vehicle.Motorcycle) return true;
        if(v instanceof Vehicle.Car)        return s.type==Spot.Type.COMPACT || s.type==Spot.Type.LARGE;
        if(v instanceof Vehicle.Bus)        return s.type==Spot.Type.LARGE;
        return false;
    }

    // Aloca 1 vaga (moto/carro)
    public boolean allocateOne(Vehicle v){
        for(Node cur=head; cur!=null; cur=cur.next){
            if(cur.s.isFree() && canFit(v, cur.s)){
                cur.s.occupant = v;
                return true;
            }
        }
        return false;
    }

    // Aloca ônibus: 5 LARGE consecutivas na MESMA fileira/nível
    public boolean allocateBus(Vehicle.Bus bus){
        for(Node cur=head; cur!=null; cur=cur.next){
            Spot s = cur.s;
            if(s.isFree() && s.type==Spot.Type.LARGE){
                int need=5, lvl=s.level, row=s.row;
                Node run = cur;
                // checa 5 consecutivas
                while(run!=null && need>0){
                    Spot rs = run.s;
                    if(rs.isFree() && rs.type==Spot.Type.LARGE && rs.level==lvl && rs.row==row){
                        need--; run = run.next;
                    } else break;
                }
                if(need==0){
                    // aplica a alocação
                    run = cur; int left=5;
                    while(left>0 && run!=null){
                        run.s.occupant = bus;
                        run = run.next; left--;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Buscar ID da vaga pela placa
    public String findLocationByPlate(String plate) {
        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.s.occupant != null && cur.s.occupant.getPlate().equals(plate)) {
                return cur.s.id();
            }
        }
        return null;
    }

    // Print simples de uma “linha” de vagas
    public void printLine(){
        StringBuilder sb = new StringBuilder();
        for(Node cur=head; cur!=null; cur=cur.next){
            String mark = cur.s.isFree()? "_" : "X";
            sb.append("[").append(cur.s.type.name().charAt(0)).append("|").append(mark).append("]");
        }
        System.out.println(sb);
    }
}
