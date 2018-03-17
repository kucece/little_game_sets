import pygame
from flappy_bird_sprites import *
import random



class FlappyBirdGame(object):

    def __init__(self):
        print("游戏初始化")

        # 1. 创建游戏的窗口
        self.screen = pygame.display.set_mode(SCREEN_RECT.size)
        # 2. 创建游戏的时钟
        self.clock = pygame.time.Clock()
        # 3. 调用私有方法，精灵和精灵组的创建
        self.__create_sprites()

        # 4. 设置定时器事件
        pygame.time.set_timer(BIRD_DROP_EVENT, 500)
        pygame.time.set_timer(BLOCK_MOVE_EVENT, 1000)
        pygame.time.set_timer(BLOCK_POS_EVENT, 500)

    def __create_sprites(self):
        bg1 = Background()
        self.back_group = pygame.sprite.Group(bg1)
        self.bird1 = Bird()
        self.bird_group = pygame.sprite.Group(self.bird1)
        self.bird1.rect.center = bg1.rect.center
        self.pipe_up = Pipe_up()
        self.pipe_down = Pipe_down()
        self.pipe_group = pygame.sprite.Group(self.pipe_up,self.pipe_down)

    def start_game(self):
        print("游戏开始...")

        while True:
            # 1. 设置刷新帧率
            self.clock.tick(FRAME_PER_SEC)
            # 2. 事件监听
            self.__event_handler()
            # 3. 碰撞检测
            self.__check_collide()
            # 4. 更新/绘制精灵组
            self.__update_sprites()
            # 5. 更新显示
            pygame.display.update()
        while True:
            pass

    def __event_handler(self):

        for event in pygame.event.get():

            # 判断是否退出游戏
            if event.type == pygame.QUIT:
                FlappyBirdGame.__game_over()
            elif event.type == BIRD_DROP_EVENT:
                pass
            elif event.type == BLOCK_MOVE_EVENT:
                pass
            elif event.type == BLOCK_POS_EVENT:
                self.pipe_down.init_pos = random.randint(164, 348)
                self.pipe_up.init_pos = self.pipe_down.init_pos
                pass
        keys_pressed = pygame.key.get_pressed()
        if keys_pressed[pygame.K_UP]:
            self.bird1.rect.y = self.bird1.rect.y - 3 * self.bird1.speed
        elif keys_pressed[pygame.K_SPACE]:
            self.bird1.rect.y = self.bird1.rect.y - 3 * self.bird1.speed

    def __check_collide(self):
        pipes = pygame.sprite.groupcollide(self.bird_group, self.pipe_group, False, False)
        if len(pipes) > 0 :
            print("game over")
            FlappyBirdGame.__game_over()
        pass

    def __update_sprites(self):
        pass
        self.back_group.update()
        self.back_group.draw(self.screen)
        self.bird_group.update()
        self.bird_group.draw(self.screen)
        self.pipe_group.update()
        self.pipe_group.draw(self.screen)

    @staticmethod
    def __game_over():
        print("游戏结束")

        pygame.quit()
        exit(0)

if __name__ == '__main__':

    # 创建游戏对象
    game = FlappyBirdGame()

    # 启动游戏
    game.start_game()
