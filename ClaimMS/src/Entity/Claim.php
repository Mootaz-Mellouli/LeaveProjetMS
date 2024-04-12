<?php

namespace App\Entity;


use ApiPlatform\Metadata\ApiResource;
use App\Repository\ClaimRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ClaimRepository::class)]
#[ApiResource]
class Claim
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $Description = null;

    #[ORM\Column(type: Types::DATETIME_MUTABLE)]
    private ?\DateTimeInterface $ClaimDate = null;

    #[ORM\Column(nullable: true)]
    private ?bool $FeedBackEmployee = null;

    #[ORM\Column(nullable: true)]
    private ?string $matriculeClient;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(?string $Description): static
    {
        $this->Description = $Description;

        return $this;
    }

    public function getClaimDate(): ?\DateTimeInterface
    {
        return $this->ClaimDate;
    }

    public function setClaimDate(\DateTimeInterface $ClaimDate): static
    {
        $this->ClaimDate = $ClaimDate;

        return $this;
    }

    public function isFeedBackEmployee(): ?bool
    {
        return $this->FeedBackEmployee;
    }

    public function setFeedBackEmployee(?bool $FeedBackEmployee): static
    {
        $this->FeedBackEmployee = $FeedBackEmployee;

        return $this;
    }

    public function getMatriculeClient(): string
    {
        return $this->matriculeClient;
    }

    public function setMatriculeClient(string $matriculeClient): void
    {
        $this->matriculeClient = $matriculeClient;
    }

}
