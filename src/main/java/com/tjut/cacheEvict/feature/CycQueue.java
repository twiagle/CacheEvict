package com.tjut.cacheEvict.feature;

import java.util.Arrays;

/**
 * @author tb
 * @date 7/22/19-3:19 PM
 *
 * @date 7/2/30-4:38 PM
 * each array element of arr stores interval length between two requests of an identical object
 * rear is latest, head is far
 */
public class CycQueue {//
        private int[] arr;//previous Request sequence
        private int front;//头指针，若队列不为空，指向队头元素
        private int rear; //尾指针，若队列不为空，指向队列尾元素的下一个位置
        private int maxSize;//power of 2 to support & (maxSize -1)

        public CycQueue(int maxSize) {//e.g. features are delta 1 2 3 4 5 6 7
            this.maxSize = maxSize +1;//so 8
            arr = new int[this.maxSize];//循环队列最大8 7+black
            Arrays.fill(arr, Integer.MAX_VALUE);// initial MAX_VALUE for those interval are not available
            front = rear = 0;
        }
//resize depends on whether learn++nse supports omitted feature
//        public CycQueue(CycQueue old){
//            int oldSize = old.maxSize;
//            maxSize = oldSize << 1;
//            arr = Arrays.copyOf(old.arr, maxSize);
//        }

        //入队前判满
        public void enQueue(int e) {
            //队列头指针在队尾指针的下一位位置上  说明满了 override it!
            if (((rear+1)&(maxSize-1)) == front) {
                front = (front + 1) & (maxSize-1);;
            }
            arr[rear] = e;
            rear = (rear + 1) & (maxSize-1);
        }

        public CycQueue destroyQueue() {
            rear = front = 0;
            arr = null;
            return this;
        }

        //get delta 1 to delta 7 feature, still can optimize x2 by ArraysCopy head to size
        public int[] getDeltas(){
            int[] deltas = new int[maxSize];
            for (int i = 1; i < queueLength(); i++) {
                deltas[i] = getLastElement(i);
            }
            return deltas;
        }

        //rear store nothing, so lastWhat should >= 1
        private int getLastElement(int lastWhat) {
            assert lastWhat >=0 ;
            if (lastWhat > queueLength()) {
                return Integer.MAX_VALUE;//default value
            }
            return arr[(rear - lastWhat + maxSize) &(maxSize-1)];
        }

        private Integer queueLength() {
            return (rear - front + maxSize) & (maxSize-1); //求环形队列的元素个数
        }
}
