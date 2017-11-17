import tool.const as const
import os
import errno
import random

class Changer():
    def __init__(self):
        self.change_to_more_symbol = True
        self.change_to_low_symbol = True
        self.change_to_and_symbol = True
        self.change_to_or_symbol = True

    def set_flag(self,flag):
        if not (flag & const.flag_change_to_more_symbol):
            self.change_to_more_symbol = False

        if not (flag & const.flag_change_to_low_symbol):
            self.change_to_low_symbol = False
    
        if not (flag & const.flag_change_to_and_symbol):
            self.change_to_and_symbol = False
        
        if not (flag & const.flag_change_to_or_symbol):
            self.change_to_or_symbol = False
    
    def handle_file(self, src_file, write_file):
        with open(src_file) as fr:
            if not os.path.exists(os.path.dirname(write_file)):
                try:
                    os.makedirs(os.path.dirname(write_file))
                except OSError as exc:
                    if exc.errno != errno.EEXIST:
                        raise
            with open(write_file, 'w+') as fw:
                for line in fr.readlines():
                    modify_index = []
                    if self.change_to_and_symbol:
                        line, index = self.change_symbol(line,"||","&&", modify_index)
                        modify_index.append(index)
                        if index != -1:
                            fw.write('/* Fault:mutation insert code */\n')
                    if self.change_to_or_symbol:
                        line, index = self.change_symbol(line,"&&","||", modify_index)
                        modify_index.append(index)
                        if index != -1:
                            fw.write('/* Fault:mutation insert code */\n')
                    if self.change_to_low_symbol:
                        line, index = self.change_symbol(line,"<",">", modify_index)
                        modify_index.append(index)
                        if index != -1:
                            fw.write('/* Fault:mutation insert code */\n')
                    if self.change_to_more_symbol:
                        line, index = self.change_symbol(line,">","<", modify_index)
                        modify_index.append(index)
                        if index != -1:
                            fw.write('/* Fault:mutation insert code */\n')
                    fw.write(line)

    def change_symbol(self, line, fromm, to, modify_index):
        v = random.randint(1,10)
        index = -1
        if v < 5:
            index = line.find(fromm)
            if index != -1 and (index not in modify_index):
                line = line.replace(fromm,to) 
                return line, index
        return line, -1