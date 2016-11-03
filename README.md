# CoordinatorLayoutTest
CoordinatorLayout布局使用

     @Override
     public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
         return dependency instanceof MyView;
     }
    
     //每次dependency位置发生变化，都会执行onDependentViewChanged方法
        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {

        return true;
    }
       